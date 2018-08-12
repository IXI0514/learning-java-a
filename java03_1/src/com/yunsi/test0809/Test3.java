package com.yunsi.test0809;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;

public class Test3 {
	public static void main(String[] args) throws Exception {
		File f = new File("e:/in.txt");
		test(f);
		test1(f);
		test2(f);
	}
	public static void test(File f) throws Exception {
		
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		raf.setLength(5);
		raf.seek(4);
		raf.write('a');
		
		raf.seek(1);
		raf.write('b');
		raf.write('c');
		raf.write('d');
	// bcda
		raf.seek(1);
		System.out.println((char)raf.read());
		raf.close();
	}
	public static void test1(File f) throws Exception {
		Writer w = new FileWriter(f,true);
		w.write('a');
		w.write('中');//可以使用中文
		w.close();
	}
	public static void test2(File f) throws Exception {
		Reader r = new FileReader(f);
	
		int readlen = -1;
		while ((readlen=r.read())!=-1) {
			System.out.print(" "+(char)readlen);
		}
		r.close();
	}
}
