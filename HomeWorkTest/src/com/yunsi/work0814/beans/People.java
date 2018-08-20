package com.yunsi.work0814.beans;

import com.yunsi.work0815.bean.Student;

/**
 * People抽象类
 *	作为父类，提供共有特征
 * @author ShenBL
 *
 */
public  abstract class People implements Comparable<People>{
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
		
		String str = "[ "+"编号："+getPid()+"、姓名："+getName()+"、性别："+getSex()+"、年龄："+getAge()+"、职业："+getJob();
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
	@Override
	public int compareTo(People o) {
		int com = this.getPid().compareTo(o.getPid());
		return com==0?1:com;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
			
			
		People other = (People) obj;
		if (pid == null) {
			if (other.pid != null) {
				System.out.println("编号重复，添加失败！！");
				return false;
			}
				
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}
	
/*	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		People other = (People) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}
	*/

}
