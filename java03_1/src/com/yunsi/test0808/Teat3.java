package com.yunsi.test0808;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * outputStream
 * 
 */
public class Teat3 {
	public static void main (String[] args) {
		
		test2();
	}
	
	
	public static void test1() throws Exception {
		OutputStream out = new FileOutputStream("e:/out.txt",true);
		out.write('a');
		out.write('a');
		out.close();
		
		
	}
	public static void test2() {
		InputStream in = null;
		InputStream in2 = null;
		OutputStream out =null;
		long start =System.currentTimeMillis();
		try {
			File f =new File("e:/in.txt");
			File f2 =new File("e:/in2.txt");
			File f3 =new File("e:/out2.txt");
			in = new FileInputStream(f);
			in2 = new FileInputStream(f2);
			out = new FileOutputStream(f3,true);
			
			/*int d=-1;
			while ((d=in.read())!=-1) {
				out.write((char)d);
			}*/
			
			byte[] rbyte=new byte[5];
			int d =-1;
			while ((d=in.read(rbyte))!=-1) {
				out.write(rbyte,0,d);
			}
			int d2 =-1;
			while ((d=in2.read(rbyte))!=-1) {
				out.write(rbyte);//不加限定位置会输出内存遗留的内容
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end =System.currentTimeMillis();
		System.out.println("完成！！ 时间："+(end-start)+"ms");
		
	}
}
