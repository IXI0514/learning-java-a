package com.yunsi.test0807;

import java.io.File;

public class Test2 {
	/*public static void main (String[] args) {
		test1();
	}*/
	public static void test1(){
		File f = new File("F:\\");
		String[] children =f.list();
		//list() File对象调用后，返回数组
		File[] fs= f.listFiles();
		String a = fs[1].getName();
		System.out.println(a);
		if (children!=null && children.length>0) {
			for(String child : children) {
				
				File fc = new File(f,child);
				System.out.println(fc.isFile());
			}
			}
	}
}
