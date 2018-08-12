package com.yunsi.test0809;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Test1 {
	public static void main (String[] args) throws Exception{
		test1();
		test2();
	}
	public static void test1() throws Exception{
		InputStream b = new FileInputStream("e:/in.txt");
		DataInputStream datain = new DataInputStream (b);
		OutputStream a=new FileOutputStream("e:/in.txt");
		DataOutputStream dataout = new DataOutputStream (a);
		//通过基本数据类型的输入流输入
		dataout.writeByte(1);
		dataout.writeInt(10);
		dataout.writeLong(10);
		dataout.writeFloat(3.3f);
		dataout.writeChar('a');
		dataout.writeUTF("字符串");
		dataout.close();
		System.out.println(datain.readByte());
		System.out.println(datain.readInt());
		System.out.println(datain.readLong());
		System.out.println(datain.readFloat());
		System.out.println(datain.readChar());
		System.out.println(datain.readUTF());
		datain.close();
	}
	
	public static void test2() {
		byte[] data ="qwerttyasdsa".getBytes();
		
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		int ren = -1;
		while ((ren= bais.read())!=-1) {
			System.out.print((char)ren+" ");
		}
		try {
			bais.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
