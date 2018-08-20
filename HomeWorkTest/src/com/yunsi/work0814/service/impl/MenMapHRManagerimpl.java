package com.yunsi.work0814.service.impl;

import java.util.Map;
import java.util.TreeMap;

import com.yunsi.work0814.beans.People;
import com.yunsi.work0814.exception.HRMException;
import com.yunsi.work0814.service.HRManagerIF;
/**
 * 
 * map¿‡
 * @author ShenBL
 *
 */
public class MenMapHRManagerimpl implements HRManagerIF {

	public  MenMapHRManagerimpl() {
		Map<String, People> peoples = new TreeMap<>();
	}
	
	@Override
	public void add(People a) throws HRMException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String pid) throws HRMException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String pid) throws HRMException {
		// TODO Auto-generated method stub

	}

	@Override
	public void show(String pid) throws HRMException {
		// TODO Auto-generated method stub

	}

	@Override
	public void showAll() throws HRMException {
		// TODO Auto-generated method stub

	}

}
