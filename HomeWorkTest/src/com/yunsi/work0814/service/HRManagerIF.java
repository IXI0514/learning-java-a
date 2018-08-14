package com.yunsi.work0814.service;



import com.yunsi.work0814.beans.People;
import com.yunsi.work0814.exception.HRMException;

public interface HRManagerIF {


	//���
	public void add(People a) throws HRMException;
	//ɾ��
	public void delete(String pid) throws HRMException;
	//����
	public void update (String pid) throws HRMException;
	//��ʾ
	public void show(String pid) throws HRMException;
	public void showAll() throws HRMException;
}
