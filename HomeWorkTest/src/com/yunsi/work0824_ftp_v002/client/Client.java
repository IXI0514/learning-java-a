package com.yunsi.work0824_ftp_v002.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
	private Socket socket;
	private BufferedInputStream sin ;//socket输入流
	private BufferedOutputStream sout ;//socket输出流
	private BufferedInputStream lin;//本地输入流
	private BufferedOutputStream lout;//本地输出流
	private BufferedReader keybuff;//从键盘读取
	private byte[] buflen = new byte[4];//读取时用的数组1
	private byte[] inbuf = new byte[1024];//读取时用的数组
	
	//private byte[] sobuf ;//传输
	private String serverpath;//当前服务端目录  全路径
	private String localpath = "F:/temp";//当前本地目录
	
	
	//private int NameMaxL;
	private long SizeMaxL;//size最大字符长度
	private long DirSize ;//文件夹的大小
		
	public Client(Socket socket) {
		this.socket = socket;
	}	
	
	
	/**
	 * 	构造IO流
	 * @throws IOException 
	 */
	public  void start() throws IOException  {
		sin = new BufferedInputStream(socket.getInputStream());
		sout = new BufferedOutputStream(socket.getOutputStream());
		keybuff = new BufferedReader(new InputStreamReader(System.in));
		//获取返回目录
		int len = sin.read(inbuf);
		serverpath=new String(inbuf,0,len);//接收初始服务端目录
		System.out.println("当前服务端目录："+serverpath);//check
		System.out.println("outpath2");
		while (true) {//不断读取控制台的命令
			System.out.print("ftp>>");
			String input = keybuff.readLine();//从键盘读一行(命令)
			if (input.equalsIgnoreCase("exit")) {//退出
				sout.write("exit".getBytes());//发送退出命令到服务端
				sout.flush();
				break;//退出while()	
			}
			actionList(input);//处理命令
		}
		socket.close();	
	}
	/**
	 * actonlist 
	 * 管理命令目录
	 * @param command
	 * @throws IOException
	 */
	private  void actionList(String command) throws IOException {
		if (command.equalsIgnoreCase("help")) {
			System.out.println("pwd：显示服务端目录\n"
					+ "lpwd：显示本地工作目录\n"
					+ "dir：显示服务端工作目录\n"
					+ "ldir：显示本地工作目录\n"
					+ "cd：在服务端工作目录跳转\n"
					+ "lcd：在本地工作目录跳转\n"
					+ "get：获取当前服务端工作目录中的文件\n"
					+ "put：上传当前本地工作目录中的文件\n"
					+ "exit：退出，与服务器断开连接"); 
		} else if (command.equalsIgnoreCase("pwd")) {
			System.out.println(serverpath);
		} else if (command.equalsIgnoreCase("lpwd")) {
			System.out.println(localpath);
		} else if (command.equalsIgnoreCase("dir")) {
			serverDir(command);
		} else if (command.equalsIgnoreCase("ldir")) {
			localDir();
		} else if (command.startsWith("cd")) {
			serverCd(command);
		} else if (command.equalsIgnoreCase("lcd")) {
			localCD(command);
		} else if (command.startsWith("get")) {
			getFile(command);
		} else if (command.equalsIgnoreCase("put")) {
			put(command);
			
		}
		
	}
	
	/**
	 * dir
	 * 处理服务端的打印文件夹条目
	 * @param command
	 * @throws IOException 
	 */
	private void serverDir(String command) throws IOException {
		command = "dir@"+serverpath;
		sout.write(command.getBytes());
		sout.flush();
		
		int len = 0;//读取回复
		StringBuilder builder=new StringBuilder();
		while(((len=sin.read(inbuf))!=0)&&!((new String(inbuf,0,len)).equals("end"))) {
			System.out.println("2");
			builder.append(new String(inbuf,0,len));
		}
		  		
		String[] redirs = (builder.toString()).split("#");//按行输出目录信息
		for (String dir : redirs) {
			System.out.println(dir);
		}	
	}//end serverDir
	
	/**
	 * server cd 
	 * 处理服务端的跳转
	 * @param command
	 * @throws IOException
	 */
	private void serverCd(String command) throws IOException {
		String pathname =command.substring(2).trim();//目录名称
		if (pathname.equalsIgnoreCase("..")) {
			File file =new File(serverpath);
			pathname =file.getParent();//获取父目录传到服务端验证
		}else {
			pathname =serverpath+File.separator+pathname;//验证跳转之后的目录
		}
		sout.write(("cd@"+pathname).getBytes());
		sout.flush();
		int len = sin.read(inbuf);//读取回复
		String  restr= new String(inbuf,0,len);
		if (!restr.startsWith("Error")) {					
			serverpath=pathname;
		}
		System.out.println(restr);
		
	}//end serverCd
	
	/**
	 * getFile
	 * 处理获取文件请求
	 * @param command
	 * @throws IOException
	 */
	private void getFile(String command) throws IOException {
		String pathname =command.substring(3).trim();//文件名称
		command ="get@"+serverpath+File.separator+pathname;//文件目录
		sout.write(command.getBytes());
		sout.flush();
		
		int len = sin.read(inbuf);
		String restr= new String(inbuf,0,len);//确认返回值
		System.out.println(restr);//check
		//确认服务端是否存在文件 ,再执行操作
		if(restr.startsWith("start")) {
			lout = new BufferedOutputStream(new FileOutputStream(localpath+File.separator+pathname));
			System.out.println("0");
			int i=1;
			
			while (true) {
				len=sin.read(buflen);
				String temp = new String(buflen);
				System.out.println(temp);//
				if(temp.contains("end")) {
					lout.flush();
					System.out.println("获取文件["+pathname+"]完成");
					break ;
				}

				int blen = Integer.parseInt(temp);
				inbuf = new byte[blen];
				len=sin.read(inbuf);
				
				i++;
				System.out.println(i+" :"+temp);
				lout.write(inbuf);//写到本地目录
				
			}
				
		}else if (restr.startsWith("Error")) {
			System.out.println("re:"+restr);
		} else {
			System.out.println("未知返回值："+restr);
		}
	}//end getFile
	
	/**
	 * put
	 * 处理上传文件
	 * @param command
	 * @throws IOException 
	 */
	private void put(String command) throws IOException {
		String pathname =command.substring(3).trim();//文件名称
		File path = new File(localpath+File.separator+pathname);
		//验证本地目录是否存在 且传输的是文件
		if(path.exists()&&path.isFile()) {
			sout.write((serverpath+File.separator+pathname).getBytes());//传输要在服务端创建的路径
			sout.flush();
			lin = new BufferedInputStream(new FileInputStream(path));
			int len = -1;
			while((len=lin.read(inbuf))!=-1) {
				sout.write(inbuf, 0, len);
				sout.flush();
			}
			sout.write("end".getBytes());
			sout.flush();
		}else {
			System.out.println("输入文件参数不正确！！！");
		}	
		
	}

	

	
	
	

	

	/**
	 * lcd
	 * 处理本地文件夹跳转
	 * @param pathname
	 */
	private void localCD(String command) {
		String pathname =command.substring(3).trim();
		if (pathname.equalsIgnoreCase("..")) {
			File file =new File(serverpath);
			pathname =file.getParent();
		}else {
			pathname =serverpath+File.separator+pathname;
		}
		File path = new File(pathname);
		if (!path.exists()) {
			System.out.println("Error：目录不存在！！！");//error是客户端识别标识
		}else if (path.isFile()){
			System.out.println("Error：目录是文件不支持跳转！！！");
		}else {
			localpath = pathname;
			System.out.println("当前本地目录："+localpath);	
		}
	}
	
	/**
	 * 
	 * 格式化输出 (获取+时间+类型+大小+文件名)字符串 
	 */
	private void localDir() {
		File file = new File(localpath);
		//System.out.println(file.length());
		File[] files = file.listFiles();
		if (files!=null&&files.length>0) {
			demo(files);//查找size最长1
			for(File list : files) {
				//格式：时间   类型  大小  文件名
				StringBuilder builder=new StringBuilder();
				SimpleDateFormat sim =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date(list.lastModified());
				builder.append(" "+sim.format(date));
				//类型
				String type = list.isDirectory()?" <DIR>    ":" <FILE>   ";
				builder.append(type);
				//大小
				String s1= getsize(list);
				builder.append(s1);
				for(int i =0 ;i<=(SizeMaxL-s1.length());i++) {
					builder.append(" ");
				}
				//文件名
				String s2 = list.getName();
				builder.append("   ").append(s2);
				System.out.println(builder);
			}
		} else {
			System.out.println("当前目录为空");;
		}
	}
	/**
	 * 
	 * 	输出目录命令
	 * 		1.5 找到最长的
	 * 		2获取文件大小
	 * 		2.5获取文件夹大小
	 * 		2.6加千位分隔符
	 * @param file
	 */
	private void demo(File[] file) {//1.5 找到最长的
		//NameMaxL = 0;
		SizeMaxL = 0;
		for (File f : file) {
			//int n = f.getName().length();
			int s = getsize(f).length();
			//NameMaxL = n>NameMaxL?n:NameMaxL;
			SizeMaxL = s>SizeMaxL?s:SizeMaxL;
		}
	}
	private String getsize(File f) {//2获取文件大小
		if (f.isFile()) {
			return formate( f.length());
		}else {
			DirSize=0;
			getDirSize(f);
			return formate(DirSize);
		}
	}
	private void getDirSize(File file) {//2.5获取文件夹大小
		File[] files = file.listFiles();
		if (files!=null&&files.length>0) {
			for (File f : files) {
				if (f.isDirectory()) {
					getDirSize(f);
				}else {
					DirSize +=f.length();
				}
			}
		}
	}
	
	private String formate(long num) {//2.6加千位分隔符
		String str = String.valueOf(num);
		StringBuilder builder = new StringBuilder();
		if (str.length()>3) {
			int a = str.length()%3;
			builder.append(str.substring(0, a));
			if (a!=0) {
				builder.append(",");
			}
			for (int i = 0; i < str.length()-a; i++) {
				if (i!=0&&i%3==0) {
					builder.append(",");
				}
				builder.append(str.charAt(i));	
			}
			//System.out.println(builder);//检查处理结果
		}
		return builder.toString();
	}
	
}
