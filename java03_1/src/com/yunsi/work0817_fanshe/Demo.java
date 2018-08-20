package com.yunsi.work0817_fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 操作反射 --练习 --测试
 * @author ShenBL
 *
 */
public class Demo {
	
	public static void main(String[] args) throws Exception  {
		
		Demo1();	
		Demo2();
		
		Demo4();
		Demo3();
	}

	private static void Demo4() throws  Exception{//获取构造方法并创建对象
		// TODO Auto-generated method stub
		Class class1 = Class.forName("com.yunsi.work0817_fanshe.People");
		System.out.println("获取构造方法");
		Constructor[] constructor = class1.getConstructors();
		for (Constructor constructor2 : constructor) {
			System.out.println(constructor2);
		}
		System.out.println("获取所有构造方法");
		Constructor[] constructors3 = class1.getDeclaredConstructors();
		for (Constructor constructor2 : constructors3) {
			System.out.println(constructor2);
		}
		System.out.println("获取单个无参数的构造方法");
		Constructor con = class1.getDeclaredConstructor();
		System.out.println(con);
		System.out.println("获取有参的构造方法");
		Constructor con2 = class1.getDeclaredConstructor(String.class,String.class,String.class,int.class);
		System.out.println(con2);
		System.out.println("反射构造对象");
		System.out.println("无参数");
		Object object = con.newInstance();
		System.out.println(object);
		System.out.println("有参数");
		Object object2 = con2.newInstance("p001","shen","anhui",23);
		System.out.println(object2);
	}

	private static void Demo3() throws Exception {//获取变量
		// TODO Auto-generated method stub
		Class class1 = Class.forName("com.yunsi.work0817_fanshe.People");
		Constructor constructor = class1.getConstructor(String.class,String.class,String.class,int.class);
		Object object = constructor.newInstance("P001","BANG","CHINA",23);//创建对象
		System.out.println("获取所有变量：");//仅仅是public修饰的
		Field[] fields =class1.getFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i]);
		}
		System.out.println("获取所有变量2");//所有
		Field[] fields2 = class1.getDeclaredFields();
		for (Field field : fields2) {
			System.out.println(field);
		}
		
		System.out.println("获取单个变量的值并赋值");
		Field field = class1.getDeclaredField("pid");
		System.out.println(field);
		field.setAccessible(true);//java.lang.IllegalAccessException
		field.set(object, "p002");
		System.out.println(object);
	}

	private static void Demo2() throws Exception {//获取成员方法
		Class class1 = Class.forName("com.yunsi.work0817_fanshe.People");
		
	
	}

	private static void Demo1() {
		
	}
}
