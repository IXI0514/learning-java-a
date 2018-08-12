package com.yunsi.work0806_studentManger.stu;



public class Teacher extends Man {
	private String course;
	
	
	public Teacher() {}
	public Teacher(String mid,String name,String sex,int age,String course) {
		super(mid,name,age,sex,"教师");
		
		setCourse(course);
		
		
	}
	
	@Override
	public void show() {
		System.out.println(" 编号："+getMid()+" 姓名："+getName()+" 性别："+getSex()+" 年龄："+getAge()+"身份："+getIde()+"授课："+getCourse());
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}


	
//get set 

	
}
