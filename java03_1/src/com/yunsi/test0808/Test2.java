package com.yunsi.test0808;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**	
 *  inputStream
 *  available()
 *	read 
 *	skip
 *	close
 * @author ShenBL
 *
 */
public class Test2 {
	public static void main(String[] args) throws IOException{
		test1();
		test2();
	}
	public static void test1() throws IOException {
		File f = new File("e:/test.txt");
		InputStream stream = new FileInputStream(f);
		//available
		int len =stream.available();
		System.out.println(len);
		
		//skip
		int a=1;
		stream.skip(a);
		int data =stream.read();
		System.out.println((char)data);
		stream.close();
	}//end test1
	
	public static void test2() {
		InputStream stream =null;
		try {
			stream = new FileInputStream("e:/test.txt");
			int data=0;
			while ((data = stream.read())!=-1) {
				System.out.println((char)data+"<¡ª¡ª>"+data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(stream!=null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
		
		
		
		
	}
}
