package com.yunsi.work0813.beans.sub;

import com.yunsi.work0813.beans.People;
/**
 * 	医生实体
 * @author ShenBL
 *
 */
public class Doctor extends People {
	private String hospital;//所在医院
	private String dtype;//医生种类
	
	
	
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
		return super.showCommonInfo()+"、医院："+getHospital()+"、医科"+getDtype()+" ]";
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
