package com.yunsi.work0810_2.beans;
/**
 * 
 * Student 类  学生对象 继承People
 * @author ShenBL
 *
 */
public class Student extends People{
	private String major;
	
	
	public Student(String pid, String name, int age, String sex, String mark)  {
		super(pid,name,age,sex,"s");
		setMajor(mark);
	}

	@Override
	public void showMessage() {
		System.out.println("  专业："+getMajor()+" ]");
		
	}
	

	
	//get set 
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}






	
	
}
