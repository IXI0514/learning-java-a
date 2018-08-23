package com.yunsi.work0821_∑¥…‰2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class Test {
	public static void main(String[] args) {
		demo();
	}

	private static void demo() {
		// TODO Auto-generated method stub
		Properties properties = new Properties();
		try {
			FileReader fReader = new FileReader("../HomeWorkTest/src/com/yunsi/work0821_∑¥…‰2/properties.properties");
			properties.load(fReader);
			
			String cstr = properties.getProperty("ClassName");
			String mstr = properties.getProperty("Method");
			System.out.println(cstr+" "+ mstr);
			Class class1=Class.forName(cstr);
			Constructor constructor = class1.getConstructor();
			Object obj = class1.newInstance();
			Method method = class1.getMethod(mstr,String.class);
			method.invoke(obj, "p001");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
