package com.yunsi.work0817_反射;

/**
 * 
 * 操作反射 --练习 --被测试
 * @author ShenBL
 *
 */

public class People {
	
	
	//各种修饰符修饰的变量
	private String pid; 
	protected String name;
	public String home;
	int age;
	
	//构造方法
	public People() {}

	
	public People(String pid, String name, String home, int age) {
		super();
		this.pid = pid;
		this.name = name;
		this.home = home;
		this.age = age;
	}


	@Override
	public String toString() {
		return "People [pid=" + pid + ", name=" + name + ", home=" + home + ", age=" + age + "]";
	}

	private  People(String pid, String name) {
		this.pid = pid;
		this.name = name;
	}
	
	//普通方法
	public void test1() {
		System.out.println("无参数无返回值-public修饰");
	}
	private void test2() {
		System.out.println("无参数无返回值-private修饰");
	}
	
	
	public void test3(String str) {
		System.out.println("有参数无返回值-public修饰-："+str);
	}
	private void test30(String str) {
		System.out.println("有参数无返回值-private修饰-："+str);
	}
	public void test4(String str,int i) {
		System.out.println("多个参数无返回值-public修饰 ："+str+"-----"+i);
	}
	
	public String test5(String str) {
		str = "有参数有返回值-public修饰-";
		return str;
	}
	private String test6(int i) {
		String str="又返回值有参数 private修饰：";
		for(int j=0;j<=i;j++) {
			 str+=("循环"+j+";");
		}		
		return str;
	}
	
	
} 
