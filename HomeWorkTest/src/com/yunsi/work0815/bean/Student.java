package com.yunsi.work0815.bean;
/**
 * 
 * 	学生实体
 * @author ShenBL
 *
 */
public class Student {
	private String sid;//编号
	private String name;//姓名
	private int age;//年龄
	private int height;//身高
	
	

	public String printInfo() {//输出到文件格式
		return "Student@" + sid + "#" + name + "#" + age + "#" + height;
	}
	
	
	public Student(String sid, String name, int age, int height) {
		super();
		this.sid = sid;
		this.name = name;
		this.age = age;
		this.height = height;
	}
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}


	@Override
	public String toString() {
		return "Student [sid=" + sid + ", name=" + name + ", age=" + age + ", height=" + height + "]";
	}

	
	
}
