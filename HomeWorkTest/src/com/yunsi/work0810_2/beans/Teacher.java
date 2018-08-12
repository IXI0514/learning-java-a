package com.yunsi.work0810_2.beans;
/**
 * 
 * Teacher 类  教师对象 继承People
 * @author ShenBL
 *
 */
public class Teacher extends People{
	private String course;
	
	public Teacher(String pid, String name, int age, String sex,  String mark) {
		super(pid,name,age,sex,"t");
		setCourse(mark);
	}
	
	public void showMessage() {
		System.out.println("  授课："+getCourse()+" ]");
		
	}
	
	//get set 
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
