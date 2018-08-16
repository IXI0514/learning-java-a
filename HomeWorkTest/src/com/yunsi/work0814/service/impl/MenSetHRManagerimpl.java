package com.yunsi.work0814.service.impl;
import com.yunsi.work0814.beans.People;
import com.yunsi.work0814.exception.HRMException;
import com.yunsi.work0814.frame.MainUI;
import com.yunsi.work0814.service.HRManagerIF;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *	基于内存
 * 	Set集合实现的
 * @author ShenBL
 *
 */
public class MenSetHRManagerimpl implements HRManagerIF {

	public static Set<People> peoples = new TreeSet<>();
	private People find(String pid) {//提供查找方法
		Iterator<People> iterator = peoples.iterator();
		while ( iterator.hasNext()) {
			People people= iterator.next();
			String id = people.getPid();
			if (pid.equals(id)) {
				return  people;
			}	
		}
		return null;
	}
	
	
	//添加
	@Override
	public void add(People p) throws HRMException {
		if (find(p.getPid())!=null) {
			throw new HRMException("输入的编号已存在！！");	
		}
		peoples.add(p);	
		System.out.println("添加完成！！");
	}
	//输出
	@Override
	public void delete(String pid) throws HRMException {
		People people = find(pid);
		if(people==null){
			throw new HRMException("输入的编号不存在，无法删除！！");	
		}
		peoples.remove(people);
		System.out.println("删除完成！！");	
	}
	//更新
	@Override
	public void update(String pid) throws HRMException {
		People people = find(pid);
		if(people==null){
			throw new HRMException("输入的编号不存在，无法更新！！");	
		}
		peoples.remove(people);
		MainUI ui = new MainUI();//调用更新输入界面
		People pnew = ui.common();//更新的信息
		this.add(pnew);
		
	}
	//显示信息
	@Override
	public void show(String pid) throws HRMException {
		People people = find(pid);
		if(people==null){
			throw new HRMException("输入的编号不存在，无法显示信息！！");	
		}
		System.out.println("\n"+people.showInfo()+"\n");
		
	}
	//显示所有
	@Override
	public void showAll() throws HRMException {
		if(peoples.isEmpty()) {
			throw new HRMException("当前无在册人员！！");
		}
		Iterator<People> iterator = peoples.iterator();
		while ( iterator.hasNext()) {
			People people= iterator.next();
			System.out.println("\n"+people.showInfo()+"\n");
		}
		
	}
	


}
