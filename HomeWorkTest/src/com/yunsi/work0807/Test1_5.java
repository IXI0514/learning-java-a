package com.yunsi.work0807;

import java.io.File;
import java.io.FilenameFilter;

public class Test1_5 {
	public static int fsum=0;
	public static int ffsum=0;//��̬����������  �����ٴε��õ�����
	
	public static void main (String[] args) {
		File f = new File("F:/YunSi/2/HomeWork0807/root");
		
		System.out.println("------------------Start----------------------\n");
		find(f,true);
		System.out.println("\n ����>��Ŀ¼���ļ� fsum:"+fsum+"��");
		
		System.out.println("\n----------------------------------------\n");
		
		find(f,false);
		System.out.println("\n ����>��Ŀ¼���ļ��� ffsum:"+ffsum+"��");
		
		System.out.println("\n-------------------End---------------------");
		
		find(f,true);
		System.out.println("\n ����>��Ŀ¼���ļ� fsum:"+fsum+"��");
	}//end main
	public static void find(File f,boolean b) {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir,String filename) {
				File file =new File(dir,filename);
				if(b) {
					if (file.isFile()) {//�������ļ�
						fsum++;
						return true;
					}
					if (file.isDirectory()){//���ļ����в���
						find(file,true);
					}
				}else if (!b){
					if (file.isDirectory()){//���ļ���
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
