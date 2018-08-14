package com.yunsi.work0814.service;



import com.yunsi.work0814.beans.People;
import com.yunsi.work0814.exception.HRMException;

public interface HRManagerIF {


	//添加
	public void add(People a) throws HRMException;
	//删除
	public void delete(String pid) throws HRMException;
	//更新
	public void update (String pid) throws HRMException;
	//显示
	public void show(String pid) throws HRMException;
	public void showAll() throws HRMException;
}
