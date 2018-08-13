package com.yunsi.work0813.beans;

import java.awt.JobAttributes;
/**
 * People抽象类
 *	作为父类，提供共有特征
 * @author ShenBL
 *
 */
public abstract class People {
	private String pid;//编号
	private String name;//姓名
	private String sex;//性别
	private int age;//年龄
	private String job;//工作

	
	
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
		
		String str = "[ "+"编号"+getPid()+"、姓名"+getName()+"、性别"+getSex()+"、年龄"+getAge()+"、职业"+getJob();
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
