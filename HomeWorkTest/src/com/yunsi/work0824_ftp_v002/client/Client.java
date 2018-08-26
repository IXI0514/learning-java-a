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
	private BufferedInputStream sin ;//socket������
	private BufferedOutputStream sout ;//socket�����
	private BufferedInputStream lin;//����������
	private BufferedOutputStream lout;//���������
	private BufferedReader keybuff;//�Ӽ��̶�ȡ
	private byte[] buflen = new byte[4];//��ȡ���鳤��
	private byte[] inbuf = new byte[1024];//��ȡʱ�õ�����
	
	//private byte[] sobuf ;//����
	private String serverpath0;//��ǰ�����Ŀ¼  ����
	private String serverpath;//��ǰ�����Ŀ¼ 
	private String localpath0 = "F:/temp";//��ǰ����Ŀ¼ ����
	private String localpath = "F:/temp";//��ǰ����Ŀ¼
	
	
	//private int NameMaxL;
	private long SizeMaxL;//size����ַ�����
	private long DirSize ;//�ļ��еĴ�С
		
	public Client(Socket socket) {
		this.socket = socket;
	}	
	
	
	/**
	 * 	����IO��
	 * @throws IOException 
	 */
	public  void start() throws IOException  {
		sin = new BufferedInputStream(socket.getInputStream());
		sout = new BufferedOutputStream(socket.getOutputStream());
		keybuff = new BufferedReader(new InputStreamReader(System.in));
		//��ȡ����Ŀ¼
		int len = sin.read(inbuf);
		serverpath=new String(inbuf,0,len);//���ճ�ʼ�����Ŀ¼
		serverpath0 = new String(inbuf,0,len);
		System.out.println("��ǰ�����Ŀ¼��"+serverpath);//check
		
		while (true) {//���϶�ȡ����̨������
			System.out.print("ftp>>");
			String input = keybuff.readLine();//�Ӽ��̶�һ��(����)
			if (input.equalsIgnoreCase("exit")) {//�˳�
				sout.write("exit".getBytes());//�����˳���������
				sout.flush();
				System.out.println("����������Ͽ�����...");
				break;//�˳�while()	
			}
			actionList(input);//��������
		}
		socket.close();	
	}
	/**
	 * actonlist 
	 * ��������Ŀ¼
	 * @param command
	 * @throws IOException
	 */
	private  void actionList(String command) throws IOException {
		if (command.equalsIgnoreCase("help")) {
			System.out.println("pwd����ʾ�����Ŀ¼\n"
					+ "lpwd����ʾ���ع���Ŀ¼\n"
					+ "dir����ʾ����˹���Ŀ¼\n"
					+ "ldir����ʾ���ع���Ŀ¼\n"
					+ "cd���ڷ���˹���Ŀ¼��ת\n"
					+ "lcd���ڱ��ع���Ŀ¼��ת\n"
					+ "get����ȡ��ǰ����˹���Ŀ¼�е��ļ�\n"
					+ "put���ϴ���ǰ���ع���Ŀ¼�е��ļ�\n"
					+ "exit���˳�����������Ͽ�����"); 
		} else if (command.equalsIgnoreCase("pwd")) {
			System.out.println("��ǰ����˹���Ŀ¼"+serverpath);
		} else if (command.equalsIgnoreCase("lpwd")) {
			System.out.println("��ǰ���ع���Ŀ¼"+localpath);
		} else if (command.equalsIgnoreCase("dir")) {
			serverDir(command);
		} else if (command.equalsIgnoreCase("ldir")) {
			localDir();
		} else if (command.startsWith("cd")) {
			serverCd(command);
		} else if (command.equalsIgnoreCase("lcd")) {
			localCd(command);
		} else if (command.startsWith("get")) {
			getFile(command);
		} else if (command.startsWith("put")) {
			upload(command);
			
		}
		
	}
	
	/**
	 * dir
	 * �������˵Ĵ�ӡ�ļ�����Ŀ
	 * @param command
	 * @throws IOException 
	 */
	private void serverDir(String command) throws IOException {
		command = "dir@"+serverpath;
		sout.write(command.getBytes());
		sout.flush();
		
		int len = 0;//��ȡ�ظ�
		StringBuilder builder=new StringBuilder();
		while(((len=sin.read(inbuf))!=0)&&!((new String(inbuf,0,len)).equals("end"))) {
			builder.append(new String(inbuf,0,len));
		}
		  		
		String[] redirs = (builder.toString()).split("#");//�������Ŀ¼��Ϣ
		for (String dir : redirs) {
			System.out.println(dir);
		}	
	}//end serverDir
	
	/**
	 * server cd 
	 * �������˵���ת
	 * @param command
	 * @throws IOException
	 */
	private void serverCd(String command) throws IOException {
		String pathname =command.substring(2).trim();//Ŀ¼����
		if (pathname.equalsIgnoreCase("..")) {
			if(serverpath.equals(serverpath0)) {
				pathname = "getparenterror";//��ֹ�Ƿ����ʷ����Ŀ¼�ĸ���Ŀ¼
			}else {
				File file =new File(serverpath);
				pathname =file.getParent();//��ȡ��Ŀ¼�����������֤
			}
		}else {
			pathname =serverpath+File.separator+pathname;//��֤��ת֮���Ŀ¼
		}
		sout.write(("cd@"+pathname).getBytes());
		sout.flush();
		int len = sin.read(inbuf);//��ȡ�ظ�
		String  restr= new String(inbuf,0,len);
		if (!restr.startsWith("Error")) {					
			serverpath=pathname;
		}
		System.out.println(restr);
	}//end serverCd
	

	/**
	 * localCd
	 * �������ļ�����ת
	 * @param pathname
	 */
	private void localCd(String command) {
		String pathname =command.substring(3).trim();
		if (pathname.equalsIgnoreCase("..")) {
			if(localpath.equals(localpath0)) {
				pathname = "getparenterror";//��ֹ���ʱ���Ŀ¼�ĸ���Ŀ¼
			}else {
				File file =new File(localpath);
				pathname =file.getParent();
			}
		}else {
			pathname =localpath+File.separator+pathname;
		}
		File path = new File(pathname);
		if (!path.exists()) {
			System.out.println("Error��Ŀ¼�����ڣ�����");
		}else if (path.isFile()){
			System.out.println("Error��Ŀ¼���ļ���֧����ת������");
		}else {
			localpath = pathname;
			System.out.println("��ǰ����Ŀ¼��"+localpath);	
		}
	}
	
	/**
	 * getFile
	 * �����ȡ�ļ�����
	 * @param command
	 * @throws IOException
	 */
	private void getFile(String command) throws IOException {
		String pathname =command.substring(3).trim();//�ļ�����
		command ="get@"+serverpath+File.separator+pathname;//�ļ�Ŀ¼
		sout.write(command.getBytes());
		sout.flush();
		
		int len = sin.read(inbuf);
		String restr= new String(inbuf,0,len);//ȷ�Ϸ���ֵ
		//System.out.println(restr);//check
		//ȷ�Ϸ�����Ƿ�����ļ� ,��ִ�в���
		if(restr.startsWith("start")) {
			lout = new BufferedOutputStream(new FileOutputStream(localpath+File.separator+pathname));
			while (true) {//�Ȼ�ȡÿ�鳤�ȣ��ٽ��н���
				sin.read(buflen);
				String temp = new String(buflen);
				//System.out.println(temp);//check
				if(temp.contains("end")) {
					lout.close();
					System.out.println("��ȡ�ļ�["+pathname+"]���");
					break ;
				}
				int blen = Integer.parseInt(temp);
				inbuf = new byte[blen];
				sin.read(inbuf);
				lout.write(inbuf);//д������Ŀ¼
			}	
		}else if (restr.startsWith("Error")) {
			System.out.println(restr);
		} else {
			System.out.println("δ֪����ֵ��"+restr);
		}
	}//end getFile
	
	/**
	 * upload
	 * �����ϴ��ļ�
	 * @param command
	 * @throws IOException 
	 */
	private void upload(String command) throws IOException {
		String pathname =command.substring(3).trim();//�ļ�����
		File path = new File(localpath+File.separator+pathname);
		//��֤����Ŀ¼�Ƿ���� �Ҵ�������ļ�
		/*System.out.println(path);
		System.out.println(path.exists()&&path.isFile());*/
		if(path.exists()&&path.isFile()) {
			sout.write(("put@"+serverpath+File.separator+pathname).getBytes());//����Ҫ�ڷ���˴�����·��
			sout.flush();
			lin = new BufferedInputStream(new FileInputStream(path));
			int len = -1;
			while((len=lin.read(inbuf))!=-1) {
				//Ҫ���೤
				String str = String.format("%04d", len);
				sout.write(str.getBytes());
				sout.write(inbuf, 0, len);
			}
			sout.flush();
			sout.write("end!".getBytes());//end ������ʶ
			sout.flush();
			System.out.println("���ϴ� #"+pathname+"# ��["+socket.getInetAddress()+":"+socket.getPort()+"]");
			lin = new BufferedInputStream(new FileInputStream(path));
		}else {
			System.out.println("��������ȷ���ļ�������");
		}	
	}//end upLoad

	/**
	 * 
	 * ��ʽ����� (��ȡ+ʱ��+����+��С+�ļ���)�ַ��� 
	 */
	private void localDir() {
		File file = new File(localpath);
		//System.out.println(file.length());
		File[] files = file.listFiles();
		if (files!=null&&files.length>0) {
			demo(files);//����size�1
			for(File list : files) {
				//��ʽ��ʱ��   ����  ��С  �ļ���
				StringBuilder builder=new StringBuilder();
				SimpleDateFormat sim =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date(list.lastModified());
				builder.append(" "+sim.format(date));
				//����
				String type = list.isDirectory()?" <DIR>    ":" <FILE>   ";
				builder.append(type);
				//��С
				String s1= getsize(list);
				builder.append(s1);
				for(int i =0 ;i<=(SizeMaxL-s1.length());i++) {
					builder.append(" ");
				}
				//�ļ���
				String s2 = list.getName();
				builder.append("   ").append(s2);
				System.out.println(builder);
			}
		} else {
			System.out.println("��ǰĿ¼Ϊ��");;
		}
	}
	/**
	 * 
	 * 	���Ŀ¼����
	 * 		1.5 �ҵ����
	 * 		2��ȡ�ļ���С
	 * 		2.5��ȡ�ļ��д�С
	 * 		2.6��ǧλ�ָ���
	 * @param file
	 */
	private void demo(File[] file) {//1.5 �ҵ����
		//NameMaxL = 0;
		SizeMaxL = 0;
		for (File f : file) {
			//int n = f.getName().length();
			int s = getsize(f).length();
			//NameMaxL = n>NameMaxL?n:NameMaxL;
			SizeMaxL = s>SizeMaxL?s:SizeMaxL;
		}
	}
	private String getsize(File f) {//2��ȡ�ļ���С
		if (f.isFile()) {
			if(f.length()<1024) {
				//System.out.println("["+f.length()+"]");
				return "<1M";
			}
			return formate( f.length()/1024)+"M";
		}else {
			DirSize=0;
			getDirSize(f);
			if(DirSize<1024) {
				//System.out.println("["+f.length()+"]");
				return "<1M";
			}
			return formate(DirSize/1024)+"M";
		}
	}
	private void getDirSize(File file) {//2.5��ȡ�ļ��д�С
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
	
	private String formate(long num) {//2.6��ǧλ�ָ���
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
			//System.out.println(builder);//��鴦����
		}
		return builder.toString();
	}
	
}
