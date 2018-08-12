package com.yunsi.test0810;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Test1 {
	public static void main(String[] args) {
		Test1 t = new Test1();
	}

	public void testWriter() {
		try(Writer w = new FileWriter("f:/in.txt");){//����
			w.write('a');
			w.write('b');
			w.write("�Ұ��й�");
			w.write("\r\n");
			w.write("I love China!");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void testBufferedWriter() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("e:/aaa.txt"))){
			bw.write('a');
			bw.write('b');
			bw.write("�Ұ�����.");
			bw.newLine();
			bw.write("���հ�����");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
