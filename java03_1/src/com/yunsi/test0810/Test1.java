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
		try(Writer w = new FileWriter("f:/in.txt");){//覆盖
			w.write('a');
			w.write('b');
			w.write("我爱中国");
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
			bw.write("我爱江苏.");
			bw.newLine();
			bw.write("江苏爱我们");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
