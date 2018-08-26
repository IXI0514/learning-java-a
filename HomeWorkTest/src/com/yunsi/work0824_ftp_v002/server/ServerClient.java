package com.yunsi.work0824_ftp_v002.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerClient {
	private  ServerSocket ssocket;//�������˿�
	private  String path;//����������Ŀ¼
	//private  int port;//�������˿�
	private static ExecutorService pool = Executors.newFixedThreadPool(20);
	
	public static void main(String[] args) {
		new ServerClient().startServer();
	}
	
	/**
	 * ��ȡ�����ļ��ı�path �Լ�port
	 */
	private  void startServer() {
		try {
			Properties properties = new Properties();
			FileReader readprop = new FileReader("../HomeWorkTest/src/com/yunsi/work0824_ftp_v002/server/properties.properties");
			properties.load(readprop);
			path = properties.getProperty("path","C:/");
			int port = Integer.parseInt(properties.getProperty("port","9090"));
			System.out.println("port:"+port);
			//���������socket�ӿ�
			ssocket = new ServerSocket(port);
			System.out.println("FTP:"+InetAddress.getLocalHost()+":"+port+"����...");
			while(true) {
				Socket socket = ssocket.accept();//�����û�socket����
				Server server = new Server(this,socket);
				pool.execute(server);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * get &&set
	 * ���ڷ�����̵߳���path
	 * @return
	 */
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
