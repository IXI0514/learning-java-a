package com.yunsi.work0810_HRM对象流.service;

import com.yunsi.work0810_HRM对象流.beans.People;

public interface IDiskHRManagement {

	public void writeData(People[] p,int idx);
	public People[] readDate();
}
