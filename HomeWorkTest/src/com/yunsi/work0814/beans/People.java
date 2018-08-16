package com.yunsi.work0814.beans;

import com.yunsi.work0815.bean.Student;

/**
 * People������
 *	��Ϊ���࣬�ṩ��������
 * @author ShenBL
 *
 */
public  abstract class People implements Comparable<People>{
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
				System.out.println("����ظ������ʧ�ܣ���");
				return false;
			}
				
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}
	

}
