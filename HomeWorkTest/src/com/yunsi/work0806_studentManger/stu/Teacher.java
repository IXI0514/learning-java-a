package com.yunsi.work0806_studentManger.stu;



public class Teacher extends Man {
	private String course;
	
	
	public Teacher() {}
	public Teacher(String mid,String name,String sex,int age,String course) {
		super(mid,name,age,sex,"��ʦ");
		
		setCourse(course);
		
		
	}
	
	@Override
	public void show() {
		System.out.println(" ��ţ�"+getMid()+" ������"+getName()+" �Ա�"+getSex()+" ���䣺"+getAge()+"��ݣ�"+getIde()+"�ڿΣ�"+getCourse());
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}


	
//get set 

	
}
