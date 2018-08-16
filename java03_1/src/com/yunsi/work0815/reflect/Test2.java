package com.yunsi.work0815.reflect;

import java.lang.reflect.Constructor;

public class Test2 {
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class stringClass = Class.forName("java.lang.String");
		
		Constructor[] constructor = stringClass.getDeclaredConstructors();//获取String类型中声明的所有构造方法
		
		if(constructor!=null) {
			System.out.println("数量："+constructor.length);
			for (Constructor constructor2 : constructor) {
				System.out.println(constructor2);
			}
		}
		
		
	}
}
