package com.yunsi.work0810_HRM对象流.service;

import com.yunsi.work0810_HRM对象流.beans.People;

public interface IHRManagement {

	public void reset();
	public void save();
	public int find(String pid);
	public void delete(String pid);
	public void showAll();
	public void showInfo(String pid);
	public void update(String pid, String name, int age, String sex, String job, String mark);
	public void showInfo(People p);
	public void add( String pid, String name, int age, String sex, String job, String mark);
	
	

}
