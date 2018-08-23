package com.yunsi.work0821_reIO;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class Test_�ֽ��� {
	public static void main(String[] args) throws IOException {
		System.out.println("�ع��ֽ���");
		demo1();
		
	}
	private static void demo1() throws IOException {
		// ���죺
		File file1 = new File("/HomeWorkTest/src/com/yunsi/work0821_reIO/aaa.text");
		System.out.println("getName: "+file1.getName());
		System.out.println("getPath: "+file1.getPath());
		System.out.println("getAbsolutePath: "+file1.getAbsolutePath());
		File file2 = new  File("../HomeWorkTest/src/com/yunsi/work0821_reIO/","aaa.txt");
		file2.createNewFile();//�����ļ�
		System.out.println(file2.exists());
		System.out.println("====��ȡ�б� ===");
		File file3 = file2.getParentFile();
		String[] strings = file3.list();
		for (String string : strings) {
			System.out.println(string);
		}
		System.out.println("====��ȡjava�ļ� ===");
		File[] files = file3.listFiles( new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().contains("java")) {
					return true;
				}
				return false;
			}
		});
		for (File file : files) {
			System.out.println(file.getName());
		}
	}
}
