package com.yunsi.work0821;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test3 {
	public static void main(String[] args) throws Exception {
		nomo();
		People people = new People("p001","shen");
		
		copy(people);
	}

	private static People copy(People p) {
		// TODO Auto-generated method stub
		try {
			Class class1 = Class.forName("com.yunsi.work0821.People");
			Constructor constructor = class1.getConstructor(String.class,String.class);
			Field[] fields = class1.getDeclaredFields();
			for (Field field : fields) {
				System.out.println(field.getName());
				System.out.println(field.getType());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Constructor constructor = class1.getConstructor();

		return null;
		
	}

	private static void nomo() throws Exception {
		// TODO Auto-generated method stub
		Class class1 = Class.forName("com.yunsi.work0821.People");
		Constructor constructor = class1.getConstructor(String.class,String.class);
		Object object = constructor.newInstance("p001","shen");
		System.out.println(object);
		
		Method[] methods = class1.getDeclaredMethods();
		for(Method method : methods) {
			System.out.println(method);
		}
		
		//获取work方法
		System.out.println("获取work：");
		Method method = class1.getDeclaredMethod("work", People.class);
		System.out.println(method);
		//method.setAccessible(true);
		
		method.invoke(object, object);
		
		//获取变量
		System.out.println("获取属性：");
		Field[] fields = class1.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field);
		}
		System.out.println("获取pid");
		Field field = class1.getDeclaredField("pid");
		System.out.println(field);
		field.setAccessible(true);
		field.set(object, "002");
		System.out.println(object);
	}
}
