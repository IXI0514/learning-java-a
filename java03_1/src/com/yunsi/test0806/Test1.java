package com.yunsi.test0806;

import java.io.IOException;
import java.io.InputStream;

public class Test1 {

	public static void main(String[] args) throws IOException {
		//InputStream in = System.in;
		second();
		/*try {//���������쳣
			second();
		} catch (IOException e) {
			System.out.println("main�е���second ����IOEexception");
		}
		 */
	
	}
	
	
	
	public static void first() {
		InputStream in = System.in;
	
		try {
			System.out.println("���룺");
			int oneByte = in.read();
			System.out.println("��Ϣ��"+((char)oneByte));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Catching");
		}		
	}

	public static void second()  throws IOException{//������׽�쳣
		InputStream in = System.in;
		System.out.println("���룺");
		int oneByte = in.read();
		if(oneByte == 'x') {
			throw new IOException("Error!");
		}
		System.out.println("��Ϣ��"+((char)oneByte));
		
		
	}

}
