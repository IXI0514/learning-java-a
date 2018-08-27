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
	private Socket socket;//�յ��Ŀͻ���socket
	private ServerClient sc;//ServerClient����
	
	private BufferedInputStream sin ;//socket������
	private BufferedOutputStream sout ;//socket�����
	private BufferedInputStream lin;//����������
	private BufferedOutputStream lout;//���������
	private byte[] buflen = new byte[4];//��ȡ���鳤��
	private byte[] inbuf = new byte[1024];//��ȡʱ�õ�����
	private String serverpath;//��ǰ�����Ŀ¼  ȫ·��
	//private String localpath;//��ǰ����Ŀ¼
	
	
	//private int NameMaxL;
	private long SizeMaxL;//size����ַ�����
	private long DirSize ;//�ļ��еĴ�С
	
	public Server(ServerClient sc,Socket socket) {
		this.sc = sc;
		this.socket = socket;
		serverpath = sc.getPath();
	}
	
	@Override
	public void run() {
		try {
			System.out.println("�û���"+socket.getInetAddress()+":"+socket.getPort()+"������...");
			sin = new BufferedInputStream(socket.getInputStream());
			sout = new BufferedOutputStream(socket.getOutputStream());
			sout.write(serverpath.getBytes());
			sout.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {//���϶�ȡ�û�����
			try {
				int len = sin.read(inbuf);
				String rec = new String(inbuf,0,len);
				if (rec.equalsIgnoreCase("exit")) {//�˳�����
					System.out.println("�ͻ���: ["+socket.getInetAddress()+":"+socket.getPort()+"]���뿪...");
					break;
				}
				actionlist(rec);//�Զ�����������д���
		} catch (SocketException e) {//����Ͽ�����
				System.out.println("�ͻ���: ["+socket.getInetAddress()+":"+socket.getPort()+"]����Ͽ�...");
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	private void actionlist(String command) throws IOException {
		String[] com = command.split("@");
		File file = new File(com[1].trim());
		//��֧�ж�
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
	 * ��ȡ������ļ�����Ŀ
	 * @param file
	 * @throws IOException
	 */
	private void serDir(File file) throws IOException {
		File[] lists=file.listFiles();
		StringBuilder builder=new StringBuilder();
		if (lists!=null&&lists.length>0) {
			demo(lists);//���ҿո�
			for(File list : lists) {
				//��ʽ��ʱ��   ����  ��С  �ļ���
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
				builder.append("#");
			}
		} else {
			builder.append("��ǰĿ¼Ϊ��");
		}
		sout.write(builder.toString().getBytes());
		sout.flush();
		System.out.println(builder);
		sout.write("end".getBytes());//end ����
		sout.flush();
		System.out.println("�ͻ���: ["+socket.getInetAddress()+":"+socket.getPort()+"]������ʾ"+file.getPath()+"Ŀ¼");	
	}//end serDir
	
	/**
	 * serCd
	 * �ж���תĿ¼����ȷ��
	 * @param file
	 * @throws IOException 
	 */
	private void serCd(File file) throws IOException {
		if (!file.exists()) {
			sout.write("Error��Ŀ¼�����ڣ�����".getBytes());//error�ǿͻ���ʶ���ʶ
		}else if (file.isFile()){
			sout.write("Error��Ŀ¼���ļ���֧����ת������".getBytes());
		}else {
			sout.write(("��ǰ�����Ŀ¼:"+file.getAbsolutePath()).getBytes());
		}
		sout.flush();
		System.out.println("�ͻ���: ["+socket.getInetAddress()+":"+socket.getPort()+"]������ת��"+file.getPath());
	}//end serCd
	
	/**
	 * serGet
	 * ��ȡҪ���ص��ļ�
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
			System.out.println("�ͻ���: ["+socket.getInetAddress()+":"+socket.getPort()+"]�����ȡ:"+file.getPath());
		}else {
			sout.write("Error���ļ������ڣ�����".getBytes());
			sout.flush();
		}
		
	}//end serGet
	/**
	 * serUpload
	 * ����ͻ����ϴ����ļ�
	 * @param file
	 * @throws IOException
	 */
	private void serUpload(File file) throws IOException {
		System.out.println("�ͻ���: ["+socket.getInetAddress()+":"+socket.getPort()+"]�ϴ��ļ�: "+file.getPath());
		lout = new BufferedOutputStream(new FileOutputStream(file));
		int len = -1;
		while((len=sin.read(inbuf))!=-1) {
			lout.write(inbuf,0,len);
			lout.flush();
		}
		//����ִ��
		System.out.println("end!!");//�������
		lout.close();
		
		
	}
	
	/**************
	 * 
	 * 	���Ŀ¼����
	 * 		0.5 �ҵ����
	 * 		1.��ȡ�ļ���С
	 * 		1.5��ȡ�ļ��д�С
	 * 		2.0��ǧλ�ָ���
	 * @param file
	 */
	//0.5�ҵ����
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
	//1.��ȡ�ļ���С
	private String getsize(File f) {
		if (f.isFile()) {
			return formate( f.length());
		}else {
			DirSize=0;
			getDirSize(f);
			return formate(DirSize);
		}
	}
	//1.5��ȡ�ļ��д�С
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
	//2.��ǧλ�ָ���
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
			//System.out.println(builder);//��鴦����
		}
		return builder.toString();
	}
	
}
