package com.yunsi.test0808;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Test1 {

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("e:/test.txt");
		
		int d1=in.read();
		int d2=in.read();
		int d3=in.read();
		int d4=in.read();
		
		System.out.println((char)d1);
		System.out.println((char)d2);
		System.out.println((char)d3);
		System.out.println((char)d4);
		in.close();
		System.out.println("=========================");
		read1();
	}

	public static void read1() {
		InputStream in = null;
		try {
			in = new FileInputStream("e:/test.txt");
			int r=in.read();
			int r1=in.read();
			System.out.println((char)r);
			System.out.println((char)r1);
		} catch (FileNotFoundException e1) {
			// 
			e1.printStackTrace();
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
	}//end read1
	//jdk7+ try..with..resource	try后面圆括号中的对象必须式AutoClosable接口的子类对象才有资格出现
	public static void read2(){
		try(InputStream in = new FileInputStream("e:/test.txt")){
			int r=in.read();
			System.out.println((char)r);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
