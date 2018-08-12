package com.yunsi.work0810.service.impl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


import com.yunsi.work0810.beans.People;
import com.yunsi.work0810.service.IDiskHRManagement;


public class DiskHRManagementImpl implements IDiskHRManagement,Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final File DATA_PATH = new File("D:/temp/data.txt");//��People�������ݵ��ļ�
	

	@Override
	public  void writeData(People[] p,int idx) {//���ļ���������
	
		FileOutputStream fo=null;
		ObjectOutputStream oo=null;
		
		if(!DATA_PATH.exists()) {
			File str = DATA_PATH.getParentFile();
			str.mkdirs();
			new File(str.getPath(),"data.txt");
		}
		try {
			fo = new FileOutputStream(DATA_PATH);
			oo = new ObjectOutputStream(fo);
			for(int i=0;i<idx;i++) {
				if(p[i]!=null) {
					System.out.println("������"+idx+"����Ա����....");
					oo.writeObject(p[i]);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oo!=null) {
				try {
					oo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public People[] readDate() {//��ȡ�ļ��е�����
		FileInputStream fi = null;
		ObjectInputStream oo=null;
		int idx = 0;
		try {//��ȡ�ж��ٸ�����
			fi = new FileInputStream(DATA_PATH);
			oo = new ObjectInputStream(fi);
			while(true) {
				try {
					oo.readObject();
					idx++;
				} catch (EOFException  e) {
					break;
				}	
			}
			if (idx==0) {
				System.out.println("�������ڲ���Ա");
			}else {
				System.out.println("�����ڲ���Ա��"+idx);
			}
		} catch (FileNotFoundException e) {//û���ļ���ֱ����ʾ
			System.out.println("�������ڲ���Ա");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(oo!=null) {
				try {
					fi.close();
					oo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		if(idx>0) {//��ȡʵ�ʵĶ��󲢷���
			try {
				fi = new FileInputStream(DATA_PATH);
				oo = new ObjectInputStream(fi);
				People[] person = new People[idx];
				for(int i= 0;i<idx;i++) {
					try {
						People p= (People) oo.readObject();
						person[i]=p;
					} catch (EOFException  e) {
						break;
					}
				}
				return person;	
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if(oo!=null) {
					try {
						fi.close();
						oo.close();
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
			}
		}
		return null;
	}
	
}
