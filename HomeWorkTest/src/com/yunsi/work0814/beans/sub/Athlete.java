package com.yunsi.work0814.beans.sub;

import com.yunsi.work0814.beans.People;
/**
 * 
 * 	�˶�Աʵ��
 * @author ShenBL
 *
 */
public class Athlete extends People {
	private String sport;//�˶���Ŀ

	
	public Athlete() {
		super();
	}

	public Athlete(String pid, String name, String sex, int age, String job, String sport) {
		super(pid, name, sex, age, job);
		this.sport = sport;
	}
	
	public  String  getInfo() {
		String str =  "Athlete@"+this.getPid()+"#"+this.getName()+"#"+this.getSex()+"#"+this.getAge()+"#"+getJob()+"#"+this.sport;
		return str;
	}
	public String showInfo() {
		String str = super.showCommonInfo()+"���˶���Ŀ��"+getSport()+" ]";
		return str;
	}
	
	//geter &&seter
	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	
	
	
}
