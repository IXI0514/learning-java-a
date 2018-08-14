package com.yunsi.work0813.beans;

import java.awt.JobAttributes;
/**
 * People������
 *	��Ϊ���࣬�ṩ��������
 * @author ShenBL
 *
 */
public abstract class People {
	private String pid;//���
	private String name;//����
	private String sex;//�Ա�
	private int age;//����
	private String job;//����

	
	
	public People() {
		super();
	}
	public People(String pid, String name, String sex, int age, String job) {
		super();
		this.pid = pid;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.job = job;
	}
	public abstract String getInfo() ;
	
	public  String showCommonInfo() {
		
		String str = "[ "+"��ţ�"+getPid()+"��������"+getName()+"���Ա�"+getSex()+"�����䣺"+getAge()+"��ְҵ��"+getJob();
		return str;
	}
	public abstract String showInfo() ;
	
	
	//geter &&seter
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}


}
