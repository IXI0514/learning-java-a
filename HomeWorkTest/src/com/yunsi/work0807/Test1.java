package com.yunsi.work0807;

import java.io.File;
import java.io.FilenameFilter;

/**
 * File常用方法：
 * 		String[] list()
 * @author Administrator
 *
 */
public class Test1 {
	static int fsum =0;//记物理文件
	static int ffsum =0;//记文件夹
	public static void main(String[] args) {
		File f = new File("F:/YunSi/2/HomeWork0807/root");
		find(f);
		System.out.println("该目录文件有 :"+fsum);
		System.out.println("该目录文件夹有 :"+ffsum);
	}//end main
	
	public static void find(File f) {
		FilenameFilter filter = new FilenameFilter() {//过滤器
			public boolean accept(File dir,String filename) {
				File aFile = new File(dir,filename);
				if (aFile.isFile()) {//物理文件
					fsum++;
					return true;
				}
				if (aFile.isDirectory()){//文件夹
					ffsum++;
					find(aFile);
					return true;
				}
				return false;
			}
		};
		 String[] children = f.list(filter);	 //过滤
	}//end find
	

}
