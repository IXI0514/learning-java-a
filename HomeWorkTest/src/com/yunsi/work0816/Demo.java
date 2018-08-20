package com.yunsi.work0816;

import java.lang.reflect.Constructor;

import com.yunsi.work0815.bean.Student;

public class Demo {
	public static void main(String[] args) throws ClassNotFoundException {
		mapDemo();
	}

	private static void mapDemo() throws ClassNotFoundException {
		
		Class class1 =Class.forName("com.yunsi.work0815.bean.Student");
		Constructor[] constructors=class1.getConstructors();
		for(Constructor com : constructors) {
			System.out.println(com+"  ");
		}
	}
	
}
