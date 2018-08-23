package com.yunsi.work0821_reIO;

import java.io.Serializable;

public class People  implements Serializable,Comparable<People>{
	private String name ;
	private String pid ;
	public People() {
		super();
		// TODO Auto-generated constructor stub
	}
	public People( String pid,String name) {
		super();
		this.name = name;
		this.pid = pid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void work(People p) {
		System.out.println(p.pid+" is work。。。。。。");
	}
	
	@Override
	public String toString() {
		return "People [name=" + name + ", pid=" + pid + "]";
	}
	@Override
	public int compareTo(People o) {
		
		return this.getPid().compareTo(o.getPid());
	}
}
