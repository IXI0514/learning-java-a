package com.yunsi.work0816;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Test2 {
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		FileReader reader= null;
		try {
			reader = new FileReader("../java03_1/src/com/yunsi/work0816/jdbc.properties");
			prop.load(reader);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(prop);
	}
	
	
}
