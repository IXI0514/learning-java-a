
package com.yunsi.work0810.beans;

import java.io.Serializable;

public abstract class People  implements Serializable{
	private String pid;
	private String name;
	private int age;
	private String sex;
	private String job;
	
	
	public People(String pid, String name, int age,String sex, String job) {
		setPid(pid);
		setName(name);
		setAge(age);
		setSex(sex);
		setJob(job);
	}
	
	public abstract void showMessage();
	
	
	//get set
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
}
