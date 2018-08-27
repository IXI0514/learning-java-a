package com.yunsi.work0824_ftp_v003.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server implements Runnable{
	private Socket socket;//收到的客户端socket
	private ServerClient sc;//ServerClient对象
	
	private BufferedInputStream sin ;//socket输入流
	private BufferedOutputStream sout ;//socket输出流
	private BufferedInputStream lin;//本地输入流
	private BufferedOutputStream lout;//本地输出流
	private byte[] buflen = new byte[4];//读取数组长度
	private byte[] inbuf = new byte[1024];//读取时用的数组
	private String serverpath;//当前服务端目录  全路径
	//private String localpath;//当前本地目录
	
	
	//private int NameMaxL;
	private long SizeMaxL;//size最大字符长度
	private long DirSize ;//文件夹的大小
	
	public Server(ServerClient sc,Socket socket) {
		this.sc = sc;
		this.socket = socket;
		serverpath = sc.getPath();
	}
	
	@Override
	public void run() {
		try {
			System.out.println("用户："+socket.getInetAddress()+":"+socket.getPort()+"已连接...");
			sin = new BufferedInputStream(socket.getInputStream());
			sout = new BufferedOutputStream(socket.getOutputStream());
			sout.write(serverpath.getBytes());
			sout.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {//不断读取用户命令
			try {
				int len = sin.read(inbuf);
				String rec = new String(inbuf,0,len);
				if (rec.equalsIgnoreCase("exit")) {//退出处理
					System.out.println("客户端: ["+socket.getInetAddress()+":"+socket.getPort()+"]已离开...");
					break;
				}
				actionlist(rec);//对读到的命令进行处理
		} catch (SocketException e) {//意外断开处理
				System.out.println("客户端: ["+socket.getInetAddress()+":"+socket.getPort()+"]意外断开...");
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	private void actionlist(String command) throws IOException {
		String[] com = command.split("@");
		File file = new File(com[1].trim());
		//分支判断
		if (com[0].equalsIgnoreCase("dir")) {
			serDir(file);
		}else if (com[0].equalsIgnoreCase("cd")) {
			serCd(file);
		}else if (com[0].equalsIgnoreCase("get")) {
			serGet(file);	
		}else if (com[0].equalsIgnoreCase("put")) {
			serUpload(file);	
		}
		
	}

	

	

	

	/**
	 *  serDir
	 * 获取服务端文件夹条目
	 * @param file
	 * @throws IOException
	 */
	private void serDir(File file) throws IOException {
		File[] lists=file.listFiles();
		StringBuilder builder=new StringBuilder();
		if (lists!=null&&lists.length>0) {
			demo(lists);//查找空格
			for(File list : lists) {
				//格式：时间   类型  大小  文件名
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
				builder.append("#");
			}
		} else {
			builder.append("当前目录为空");
		}
		sout.write(builder.toString().getBytes());
		sout.flush();
		System.out.println(builder);
		sout.write("end".getBytes());//end 结束
		sout.flush();
		System.out.println("客户端: ["+socket.getInetAddress()+":"+socket.getPort()+"]请求显示"+file.getPath()+"目录");	
	}//end serDir
	
	/**
	 * serCd
	 * 判断跳转目录的正确性
	 * @param file
	 * @throws IOException 
	 */
	private void serCd(File file) throws IOException {
		if (!file.exists()) {
			sout.write("Error：目录不存在！！！".getBytes());//error是客户端识别标识
		}else if (file.isFile()){
			sout.write("Error：目录是文件不支持跳转！！！".getBytes());
		}else {
			sout.write(("当前服务端目录:"+file.getAbsolutePath()).getBytes());
		}
		sout.flush();
		System.out.println("客户端: ["+socket.getInetAddress()+":"+socket.getPort()+"]请求跳转到"+file.getPath());
	}//end serCd
	
	/**
	 * serGet
	 * 读取要下载的文件
	 * @param file
	 * @throws IOException 
	 */
	private void serGet(File file) throws IOException {
		
		if (file.exists()&&file.isFile()) {
			sout.write("start".getBytes());
			sout.flush();
			lin = new BufferedInputStream(new FileInputStream(file));
			int len = -1;
			while((len=lin.read(inbuf))!=-1) {
				sout.write(inbuf, 0, len);
			}
			lin.close();
			sout.flush();
			System.out.println("客户端: ["+socket.getInetAddress()+":"+socket.getPort()+"]请求获取:"+file.getPath());
		}else {
			sout.write("Error：文件不存在！！！".getBytes());
			sout.flush();
		}
		
	}//end serGet
	/**
	 * serUpload
	 * 处理客户端上传的文件
	 * @param file
	 * @throws IOException
	 */
	private void serUpload(File file) throws IOException {
		System.out.println("客户端: ["+socket.getInetAddress()+":"+socket.getPort()+"]上传文件: "+file.getPath());
		lout = new BufferedOutputStream(new FileOutputStream(file));
		int len = -1;
		while((len=sin.read(inbuf))!=-1) {
			lout.write(inbuf,0,len);
			lout.flush();
		}
		//不会执行
		System.out.println("end!!");//不会输出
		lout.close();
		
		
	}
	
	/**************
	 * 
	 * 	输出目录命令
	 * 		0.5 找到最长的
	 * 		1.获取文件大小
	 * 		1.5获取文件夹大小
	 * 		2.0加千位分隔符
	 * @param file
	 */
	//0.5找到最长的
	private void demo(File[] file) {
		//NameMaxL = 0;
		SizeMaxL = 0;
		for (File f : file) {
			//int n = f.getName().length();
			int s = getsize(f).length();
			//NameMaxL = n>NameMaxL?n:NameMaxL;
			SizeMaxL = s>SizeMaxL?s:SizeMaxL;
		}
	}
	//1.获取文件大小
	private String getsize(File f) {
		if (f.isFile()) {
			return formate( f.length());
		}else {
			DirSize=0;
			getDirSize(f);
			return formate(DirSize);
		}
	}
	//1.5获取文件夹大小
	private void getDirSize(File file) {
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
	//2.加千位分隔符
	private String formate(long num) {
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
