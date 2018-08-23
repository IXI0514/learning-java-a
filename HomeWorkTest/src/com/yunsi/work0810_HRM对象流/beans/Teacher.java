package com.yunsi.work0810_HRM¶ÔÏóÁ÷.beans;

public class Teacher extends People{
	private String course;
	
	public Teacher(String pid, String name, int age, String sex,  String mark) {
		super(pid,name,age,sex,"t");
		setCourse(mark);
	}
	
	public void showMessage() {
		System.out.println("  ÊÚ¿Î£º"+getCourse()+" ]");
		
	}
	
	//get set 
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
