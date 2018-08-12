/**
 * 	2.给定一个字符串目录：String str="d:/a/b/c/d/e/f/g"   假设a目录不存在。请使用mkdir完成此路径的创建。
 * 
 */
package com.yunsi.work0807;

import java.io.File;

public class Test2 {
	
	
	public static void main (String[] args) {
		File root =new File("d:/a/b/c/d/e/f/g");
		System.out.println("是否创建文件夹 ？ "+mkd(root,root)); 
	}//end main
	
	public static boolean mkd(File e,File f) {
		if (f.exists()) {//如果目录存在返回false
			return false;
		}
		if (f!=null) {
			boolean b =f.mkdir();
			if(!b) {
				File f0=f.getParentFile();//减少一层文件夹
				//System.out.println(f0);
				mkd(e,f0);		
			}else {
				mkd(e,e);
			}
		}
		return true;//创建成功返回true	
	}//end mkd
}
