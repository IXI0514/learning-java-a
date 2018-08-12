package com.yunsi.test0808;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * BufferedInputStream
 * BufferedOutputStream
 * 
 * 
 */
public class Test4 {
	public static void main(String[] args) {
		bufferedStream();
	}
	public static void bufferedStream() {
		File fin =new File("e:/in.txt");
		File fout =new File("e:/out.txt");
		InputStream in = null;
		OutputStream out =null;
		InputStream bufferin = null;
		OutputStream bufferout = null;
		try {
			 in = new FileInputStream(fin);
			 out = new FileOutputStream(fout);
			 bufferin = new BufferedInputStream(in);
			 bufferout = new BufferedOutputStream(out);
			 byte[] read=new byte[5];
			 int d=-1;
			 while ((d=bufferin.read(read))!=-1) {
				 bufferout.write(read, 0, d);
				 //bufferout.flush();//刷新缓冲区
			 }
			 
			/*  mark
			 boolean b= bufferin.markSupported();
			 System.out.println("markSupported？ "+ b);
			 int d1=bufferin.read();
			 bufferin.mark(0);
			 int d2=bufferin.read();
			 int d3=bufferin.read();
			 bufferin.reset();
			 int d4=bufferin.read();
			 System.out.println((char)d1+"  "+(char)d2+"  "+(char)d3+"  "+(char)d4+"  ");
			 */
				
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			bufferin.close();//关闭时只要关闭操作的对象就可以了
			bufferout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
