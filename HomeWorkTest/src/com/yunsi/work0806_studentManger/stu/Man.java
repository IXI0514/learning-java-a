package com.yunsi.work0806_studentManger.stu;

public abstract class Man {
	private String mid;
	private String name;
	private int age;
	private String sex;
	private String ide;
	
	public Man() {}
	public Man(String mid,String name,int age, String sex,String ide) {
		setMid(mid);
		setName(name);
		setAge(age);
		setSex(sex);
		setIde(ide);
	}
	
	
	public abstract void show(); 
	
	//get and set
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIde() {
		return ide;
	}
	public void setIde(String ide) {
		this.ide = ide;
	}
	
	
}
