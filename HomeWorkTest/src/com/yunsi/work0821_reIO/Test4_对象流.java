package com.yunsi.work0821_reIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;



public class Test4_¶ÔÏóÁ÷ {
	static File file = new File("../java03_1/src/com/yunsi/work0821/studentout.txt");
	public static void main(String[] args) {
		People people1 = new People("p001","shen");
		People people2 = new People("p002","bang");
		write(people1);
		reader();
		SimpleDateFormat sim =  new SimpleDateFormat("yyyy-MM-dd");
		String string = sim.format(file.lastModified());
		System.out.println(string);
	}

	private static void reader() {
		// TODO Auto-generated method stub
		FileInputStream inputStream = null;
		ObjectInputStream oin =null;
		
		try {
			inputStream = new FileInputStream(file);
			oin = new ObjectInputStream(inputStream);
			People people = (People) oin.readObject();
			System.out.println(people);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream!= null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 
	}

	private static void write(People people) {
		// TODO Auto-generated method stub
		
		FileOutputStream out = null;
		ObjectOutputStream oos = null;
		
		try {
			out = new FileOutputStream(file);
			oos= new  ObjectOutputStream(out);
		
			 oos.writeObject(people);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oos!=null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
