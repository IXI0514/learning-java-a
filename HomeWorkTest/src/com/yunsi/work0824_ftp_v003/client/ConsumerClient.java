package com.yunsi.work0824_ftp_v003.client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;



public class ConsumerClient {
	private static  Socket socket;
	/**
	 * 构造本地客户端
	 * 	创建与服务器的连接
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ConsumerClient() throws IOException    {
		
		String ip ="192.168.2.89";
		int port = 8080;
		socket = new Socket(ip, port);//与服务端建立socket连接
		System.out.println("已连接到FTP:/"+ip+":"+port+"...");	
	
	}
	
	public static void main(String[] args) {
		connect();
	}
	
	private static void connect() {//等待连接服务端&&重连机制
		try {
			boolean connect = false;
			while(!connect) {
				new ConsumerClient();
				connect = true;
				new Client(socket).start();//开始等待命令
			}
		} catch ( ConnectException e) {
			System.out.println("当前无可用连接...");
			for(int i=5;i>0;i--) {
				try {
					Thread.sleep(1000);
					System.out.println(i+"秒后重试...");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			connect();
			//e.printStackTrace();
		} catch (SocketException e) {
			System.out.println("服务端意外断开连接...");
			for(int i=5;i>0;i--) {
				try {
					Thread.sleep(1000);
					System.out.println(i+"秒后重试...");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			connect();
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
