package com.yunsi.work0822.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipThread implements Runnable{
	private File zipPath;
	private ArrayList<File> list;
	
	public ZipThread(File path,ArrayList<File> list) {
		this.zipPath = path;
		this.list = list;
	}
	
	@Override
	public void run() {
		while(true) {
			Thread thread = Thread.currentThread();
			File file = null;
			synchronized (list) {
				while (list.isEmpty()) {// �ж�list�Ƿ�Ϊ��,Ϊ��ʱ��Ϣ
					try {
						System.out.println("list.size="+ list.size()+"  "+thread.getName()+" wait()....");
						list.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						//thread.interrupt();
					}
				}
				file = list.remove(0);
			}
			doZip(file);
		}
		
	}
	private  void doZip(File file) {
		Thread thread = Thread.currentThread();
		//int num = Thread.activeCount();
		//System.out.println(num);
		//zipѹ���ļ�
		
		InputStream input = null;
		ZipOutputStream zipOutputStream = null;
		try {
			input = new FileInputStream(file);
			File zipfile  =new File(zipPath.getPath()+File.separator+file.getName()+".zip");//ѹ���ļ�������
			
			zipOutputStream = new ZipOutputStream(new FileOutputStream(zipfile));
			zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
			//��ȡ&&ѹ��
			byte[] buffer = new byte[1024];
			int readLen =-1;
			while ((readLen = input.read(buffer))!=-1) {
				zipOutputStream.write(buffer,0,readLen);
			}
			zipOutputStream.closeEntry();
			System.out.println("zip: "+thread.getName()+"-- ["+zipfile.getPath()+"]");
			
			//System.out.println(list.size());//���list��С
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input!= null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (zipOutputStream!=null) {
				try {
					zipOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
