package com.yunsi.test0807;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {
	static int a=1;
	public static void main(String[] args) {
		File ff=new File("f:/temp");
		File f=new File(ff, "123.txt");
		
		System.out.println(f.getName());
		System.out.println(f.length());
		System.out.println("���ļ���"+f.isFile());
		System.out.println(f.getAbsolutePath());
		System.out.println("�Ƿ�ɶ���"+f.canRead());
		System.out.println("�Ƿ��д��"+f.canWrite());
		System.out.println("�޸�ʱ�䣺"+f.lastModified());
		System.out.println("��Ŀ¼��"+f.getParent());
		Date d = new Date(f.lastModified());
		System.out.println("�޸�ʱ�䣺"+d);
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = simp.format(d);
		System.out.println(str);
		
		System.out.println("----------------");
		f=new File(ff,"bbb.txt");
		boolean bb;
		try {
			bb = f.createNewFile();
			System.out.println(bb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File ff1 = new File("");
		try {
			ff1=File.createTempFile("aaa", ".txt",ff);
			System.out.println("create:"+ff1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File f2= new File(ff1,ff1.getName());
		//ɾ��
		boolean b=f2.delete();
		System.out.println("delete��  "+b);
		
		boolean b2=ff.delete();
		System.out.println("delete2:  "+b2);
		
		System.out.println("-----------------------");
		File path0=new File("F:\\temp0");
		delTemp(path0);
		
	}

	//delTemp()
	public static void delTemp(File f) {
		boolean re=f.delete();
		if (!re) {
			
			System.out.println("ɾ��ʧ�� ���������ļ� :"+a);
			f = new File(f,"temp");
			a++;
			System.out.println(f);
			String name0 =f.getName();
			delTemp(f);
		}
//		if(re) {
//			f=new File();
//			delete(f);
//		}
//		if(a>1) {
//			for(int i=1;i<a;i++) {
//				f.delete();
//				f = new File(f.getPath());
//				System.out.println(f);
//			}
//		}
//		
		
		
		
	}

}
