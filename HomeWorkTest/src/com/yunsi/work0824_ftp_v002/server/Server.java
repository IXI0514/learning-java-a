package com.yunsi.work0824_ftp_v002.server;

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
	 * �����ļ�
	 * @param file
	 * @throws IOException 
	 */
	private void serGet(File file) throws IOException {
		//
		if (file.exists()&&file.isFile()) {
			sout.write("start".getBytes());
			sout.flush();
			//��ȡ�����ļ� �ֽ���
			lin = new BufferedInputStream(new FileInputStream(file));
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
			System.out.println("�ͻ���: ["+socket.getInetAddress()+":"+socket.getPort()+"]�����ȡ"+file.getPath());
		}else {
			sout.write("Error���ļ������ڣ�����".getBytes());
			sout.flush();
		}
		
	}//end serGet
	
	private void serUpload(File file) throws IOException {
		// TODO Auto-generated method stub
		lout = new BufferedOutputStream(new FileOutputStream(file));
		/*	int len = -1;
		while((len=sin.read(inbuf))!=-1) {
			lout.write(inbuf);
		}
		lout.close();
		System.out.println("end11");*/
		while (true) {//�Ȼ�ȡÿ�鳤�ȣ��ٽ��н���
			sin.read(buflen);
			String temp = new String(buflen);
			//System.out.println(temp);//check
			if(temp.contains("end")) {
				lout.close();
				System.out.println("�ͻ���: ["+socket.getInetAddress()+":"+socket.getPort()+"]�ϴ�#"+file.getPath()+"#�ɹ�");
				break ;
			}
			int blen = Integer.parseInt(temp);
			inbuf = new byte[blen];
			sin.read(inbuf);
			lout.write(inbuf);//д������Ŀ¼
		}	
	}
	
	/**************
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
