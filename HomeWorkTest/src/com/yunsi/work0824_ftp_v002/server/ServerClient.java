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
	private  ServerSocket ssocket;//服务器端口
	private  String path;//服务器工作目录
	//private  int port;//服务器端口
	private static ExecutorService pool = Executors.newFixedThreadPool(20);
	
	public static void main(String[] args) {
		new ServerClient().startServer();
	}
	
	/**
	 * 读取配置文件改变path 以及port
	 */
	private  void startServer() {
		try {
			Properties properties = new Properties();
			FileReader readprop = new FileReader("../HomeWorkTest/src/com/yunsi/work0824_ftp_v002/server/properties.properties");
			properties.load(readprop);
			path = properties.getProperty("path","C:/");
			int port = Integer.parseInt(properties.getProperty("port","9090"));
			System.out.println("port:"+port);
			//创建服务端socket接口
			ssocket = new ServerSocket(port);
			System.out.println("FTP:"+InetAddress.getLocalHost()+":"+port+"启动...");
			while(true) {
				Socket socket = ssocket.accept();//接收用户socket对象
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
	 * 用于服务端线程调用path
	 * @return
	 */
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
