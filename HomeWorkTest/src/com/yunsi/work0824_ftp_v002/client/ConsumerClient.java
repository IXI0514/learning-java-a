package com.yunsi.work0824_ftp_v002.client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;



public class ConsumerClient {
	private static  Socket socket;
	/**
	 * ���챾�ؿͻ���
	 * 	�����������������
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ConsumerClient() throws IOException    {
		
		String ip ="192.168.2.89";
		int port = 8080;
		socket = new Socket(ip, port);//�����˽���socket����
		System.out.println("�����ӵ�FTP:/"+ip+":"+port+"...");	
	
	}
	
	public static void main(String[] args) {
		connect();
	}
	
	private static void connect() {//�ȴ����ӷ����&&��������
		try {
			boolean connect = false;
			while(!connect) {
				new ConsumerClient();
				connect = true;
				new Client(socket).start();//��ʼ�ȴ�����
			}
		} catch ( ConnectException e) {
			System.out.println("��ǰ�޿�������...");
			for(int i=5;i>0;i--) {
				try {
					Thread.sleep(1000);
					System.out.println(i+"�������...");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			connect();
			//e.printStackTrace();
		} catch (SocketException e) {
			System.out.println("���������Ͽ�����...");
			for(int i=5;i>0;i--) {
				try {
					Thread.sleep(1000);
					System.out.println(i+"�������...");
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
