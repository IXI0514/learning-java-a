/**
 * 	2.����һ���ַ���Ŀ¼��String str="d:/a/b/c/d/e/f/g"   ����aĿ¼�����ڡ���ʹ��mkdir��ɴ�·���Ĵ�����
 * 
 */
package com.yunsi.work0807;

import java.io.File;

public class Test2 {
	
	
	public static void main (String[] args) {
		File root =new File("d:/a/b/c/d/e/f/g");
		System.out.println("�Ƿ񴴽��ļ��� �� "+mkd(root,root)); 
	}//end main
	
	public static boolean mkd(File e,File f) {
		if (f.exists()) {//���Ŀ¼���ڷ���false
			return false;
		}
		if (f!=null) {
			boolean b =f.mkdir();
			if(!b) {
				File f0=f.getParentFile();//����һ���ļ���
				//System.out.println(f0);
				mkd(e,f0);		
			}else {
				mkd(e,e);
			}
		}
		return true;//�����ɹ�����true	
	}//end mkd
}
