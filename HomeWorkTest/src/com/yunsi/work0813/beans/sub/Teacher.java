package com.yunsi.work0813.beans.sub;

import com.yunsi.work0813.beans.People;
/**
 * 	��ʦʵ��
 * @author ShenBL
 *
 */
public class Teacher extends People {
	private String school;//����ѧУ
	private String course;//���ڿγ�
	
	
	public Teacher() {
		super();
	}
	public Teacher(String pid, String name, String sex, int age, String job,String school,String course) {
		super(pid, name, sex, age, job);
		this.course = course;
		this.school = school;
	}
	
	public  String  getInfo() {
		String str = "Teacher@"+this.getPid()+"#"+this.getName()+"#"+this.getSex()+"#"+this.getAge()+"#"+getJob()+"#"+this.school+"#"+this.course;
		return str;
	}
	//geter &&seter
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	@Override
	public String showInfo() {
		String str= super.showCommonInfo()+"��ѧУ��"+getSchool()+"���ڿΣ�"+getCourse()+" ]";
		return str;
	}

	
}
