package com.yunsi.work0813.service;



import com.yunsi.work0813.beans.People;
import com.yunsi.work0813.exception.HRMException;

public interface HRManagerIF {


	//���
	public void add(People a) throws HRMException;
	//ɾ��
	public void delete(String pid) throws HRMException;
	//����
	public void update (String pid) throws HRMException;
	//��ʾ
	public void show(String pid) throws HRMException;
	public void showAll();

	void reset();
	
}
