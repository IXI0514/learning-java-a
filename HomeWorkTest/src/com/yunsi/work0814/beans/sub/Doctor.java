package com.yunsi.work0814.beans.sub;

import com.yunsi.work0814.beans.People;
/**
 * 	ҽ��ʵ��
 * @author ShenBL
 *
 */
public class Doctor extends People {
	private String hospital;//����ҽԺ
	private String dtype;//ҽ������
	
	
	
	public Doctor() {
		super();
	}
	public Doctor(String pid, String name, String sex, int age, String job, String hospital,String dtype) {
		super(pid, name, sex, age, job);
		this.hospital = hospital;
		this.dtype = dtype;
	}
	public  String  getInfo() {
		String str = "Doctor@"+this.getPid()+"#"+this.getName()+"#"+this.getSex()+"#"+this.getAge()+"#"+getJob()+"#"+this.hospital+"#"+this.dtype;
		return str;
	}
	@Override
	public String showInfo() {
		return super.showCommonInfo()+"��ҽԺ��"+getHospital()+"��ҽ����"+getDtype()+" ]";
	}
	
	//geter &&seter
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	
	
}
