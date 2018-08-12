package com.yunsi.work0810_2.service;

import com.yunsi.work0810.beans.People;

public interface IDiskHRManagement {

	public void writeData(People[] p,int idx);
	public People[] readDate();
}
