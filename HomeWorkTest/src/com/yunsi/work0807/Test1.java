package com.yunsi.work0807;

import java.io.File;
import java.io.FilenameFilter;

/**
 * File���÷�����
 * 		String[] list()
 * @author Administrator
 *
 */
public class Test1 {
	static int fsum =0;//�������ļ�
	static int ffsum =0;//���ļ���
	public static void main(String[] args) {
		File f = new File("F:/YunSi/2/HomeWork0807/root");
		find(f);
		System.out.println("��Ŀ¼�ļ��� :"+fsum);
		System.out.println("��Ŀ¼�ļ����� :"+ffsum);
	}//end main
	
	public static void find(File f) {
		FilenameFilter filter = new FilenameFilter() {//������
			public boolean accept(File dir,String filename) {
				File aFile = new File(dir,filename);
				if (aFile.isFile()) {//�����ļ�
					fsum++;
					return true;
				}
				if (aFile.isDirectory()){//�ļ���
					ffsum++;
					find(aFile);
					return true;
				}
				return false;
			}
		};
		 String[] children = f.list(filter);	 //����
	}//end find
	

}
