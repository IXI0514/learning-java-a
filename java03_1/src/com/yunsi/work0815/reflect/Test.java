package com.yunsi.work0815.reflect;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException {
		Class class1 = String.class;
		
		String str = "abc";
		Class class2 = str.getClass();
		
		Class class3 = Class.forName("java.lang.String");
		
		System.out.println(class1);
		System.out.println(class2);
		System.out.println(class3);
	}
}
