package com.yunsi.work0815.bean;
/**
 * 
 * 	ѧ��ʵ��
 * @author ShenBL
 *
 */
public class Student {
	private String sid;//���
	private String name;//����
	private int age;//����
	private int height;//���
	
	

	public String printInfo() {//������ļ���ʽ
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
