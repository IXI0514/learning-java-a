package com.yunsi.work0806_studentManger.stu;



public class Student extends Man {
	private String major;
	
		
	public Student() {}
	public Student(String mid,String name,String sex,int age,String major)  {
		super(mid,name,age,sex,"学生");
		setMajor(major);
	}
	
	
	@Override
	public void show() {
		System.out.println(" 编号："+getMid()+"|姓名："+getName()+"|性别："+getSex()+"|年龄："+getAge()+"|身份："+getIde()+"|专业："+getMajor());
	}
	
//get set 
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}


	
	
}
