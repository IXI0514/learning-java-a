package com.yunsi.work0806_studentManger.stu;



public class Student extends Man {
	private String major;
	
		
	public Student() {}
	public Student(String mid,String name,String sex,int age,String major)  {
		super(mid,name,age,sex,"ѧ��");
		setMajor(major);
	}
	
	
	@Override
	public void show() {
		System.out.println(" ��ţ�"+getMid()+"|������"+getName()+"|�Ա�"+getSex()+"|���䣺"+getAge()+"|��ݣ�"+getIde()+"|רҵ��"+getMajor());
	}
	
//get set 
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}


	
	
}
