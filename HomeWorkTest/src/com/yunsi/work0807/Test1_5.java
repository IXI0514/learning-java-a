package com.yunsi.work0807;

import java.io.File;
import java.io.FilenameFilter;

public class Test1_5 {
	public static int fsum=0;
	public static int ffsum=0;//静态变量有限制  考虑再次调用的问题
	
	public static void main (String[] args) {
		File f = new File("F:/YunSi/2/HomeWork0807/root");
		
		System.out.println("------------------Start----------------------\n");
		find(f,true);
		System.out.println("\n ——>该目录的文件 fsum:"+fsum+"个");
		
		System.out.println("\n----------------------------------------\n");
		
		find(f,false);
		System.out.println("\n ——>该目录的文件夹 ffsum:"+ffsum+"个");
		
		System.out.println("\n-------------------End---------------------");
		
		find(f,true);
		System.out.println("\n ——>该目录的文件 fsum:"+fsum+"个");
	}//end main
	public static void find(File f,boolean b) {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir,String filename) {
				File file =new File(dir,filename);
				if(b) {
					if (file.isFile()) {//找物理文件
						fsum++;
						return true;
					}
					if (file.isDirectory()){//向文件夹中查找
						find(file,true);
					}
				}else if (!b){
					if (file.isDirectory()){//找文件夹
						ffsum++;
						find(file,false);
						return true;
					}
				}
				return false;	
			}	
		};
		String[] finded=f.list(filter);
		if(finded!=null && finded.length>0) {
			for(String str:finded) {
				System.out.print("  "+str);
			}
		}	
	}//end  find
	
}
