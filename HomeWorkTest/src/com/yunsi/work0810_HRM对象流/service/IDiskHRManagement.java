package com.yunsi.work0810_HRM������.service;

import com.yunsi.work0810_HRM������.beans.People;

public interface IDiskHRManagement {

	public void writeData(People[] p,int idx);
	public People[] readDate();
}
