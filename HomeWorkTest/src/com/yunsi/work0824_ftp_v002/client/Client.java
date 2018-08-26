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
	private byte[] buflen = new byte[4];//��ȡʱ�õ�����1
	private byte[] inbuf = new byte[1024];//��ȡʱ�õ�����
	
	//private byte[] sobuf ;//����
	private String serverpath;//��ǰ�����Ŀ¼  ȫ·��
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
		System.out.println("��ǰ�����Ŀ¼��"+serverpath);//check
		System.out.println("outpath2");
		while (true) {//���϶�ȡ����̨������
			System.out.print("ftp>>");
			String input = keybuff.readLine();//�Ӽ��̶�һ��(����)
			if (input.equalsIgnoreCase("exit")) {//�˳�
				sout.write("exit".getBytes());//�����˳���������
				sout.flush();
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
			System.out.println("2");
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
			File file =new File(serverpath);
			pathname =file.getParent();//��ȡ��Ŀ¼�����������֤
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
		System.out.println(restr);//check
		//ȷ�Ϸ�����Ƿ�����ļ� ,��ִ�в���
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
					System.out.println("��ȡ�ļ�["+pathname+"]���");
					break ;
				}

				int blen = Integer.parseInt(temp);
				inbuf = new byte[blen];
				len=sin.read(inbuf);
				
				i++;
				System.out.println(i+" :"+temp);
				lout.write(inbuf);//д������Ŀ¼
				
			}
				
		}else if (restr.startsWith("Error")) {
			System.out.println("re:"+restr);
		} else {
			System.out.println("δ֪����ֵ��"+restr);
		}
	}//end getFile
	
	/**
	 * put
	 * �����ϴ��ļ�
	 * @param command
	 * @throws IOException 
	 */
	private void put(String command) throws IOException {
		String pathname =command.substring(3).trim();//�ļ�����
		File path = new File(localpath+File.separator+pathname);
		//��֤����Ŀ¼�Ƿ���� �Ҵ�������ļ�
		if(path.exists()&&path.isFile()) {
			sout.write((serverpath+File.separator+pathname).getBytes());//����Ҫ�ڷ���˴�����·��
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
			System.out.println("�����ļ���������ȷ������");
		}	
		
	}

	

	
	
	

	

	/**
	 * lcd
	 * �������ļ�����ת
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
			System.out.println("Error��Ŀ¼�����ڣ�����");//error�ǿͻ���ʶ���ʶ
		}else if (path.isFile()){
			System.out.println("Error��Ŀ¼���ļ���֧����ת������");
		}else {
			localpath = pathname;
			System.out.println("��ǰ����Ŀ¼��"+localpath);	
		}
	}
	
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
			return formate( f.length());
		}else {
			DirSize=0;
			getDirSize(f);
			return formate(DirSize);
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
