package com.yunsi.work0822.thread;

import java.io.File;
import java.util.ArrayList;


public class FindThread implements Runnable {
	private File path;
	private ArrayList<File> list;
	private  int num  = 0;
	
	public FindThread(File path,ArrayList<File> list) {
		this.path = path;
		this.list = list;
		
	}
	
	@Override
	public void run() {
		//findoZip.find(FindoZip.path);
			find(path);
	}
	 
	private  void find(File file) {//�ж�file���� ������ļ��о͵ݹ��жϴ���������ļ��ͷŵ������еȴ�ѹ��
		boolean b= file.isDirectory();
		if(b) {
			File[] temps = file.listFiles();
			if(temps!=null) {//�����ļ�����Ϊ��
				for (File temp : temps) {
					find(temp);
				}
			}
		}else {
			synchronized (list) {
				list.add(file);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("find:"+Thread.currentThread().getName()+"--["+file.getName()+"]");
				list.notifyAll();
			}
		}
		
	}
	
}
