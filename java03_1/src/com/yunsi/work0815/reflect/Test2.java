package com.yunsi.work0815.reflect;

import java.lang.reflect.Constructor;

public class Test2 {
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class stringClass = Class.forName("java.lang.String");
		
		Constructor[] constructor = stringClass.getDeclaredConstructors();//��ȡString���������������й��췽��
		
		if(constructor!=null) {
			System.out.println("������"+constructor.length);
			for (Constructor constructor2 : constructor) {
				System.out.println(constructor2);
			}
		}
		
		
	}
}
