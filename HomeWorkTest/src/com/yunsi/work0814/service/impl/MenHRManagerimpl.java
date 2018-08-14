package com.yunsi.work0814.service.impl;


import com.yunsi.work0814.beans.People;
import com.yunsi.work0814.exception.HRMException;
import com.yunsi.work0814.frame.MainUI;
import com.yunsi.work0814.service.HRManagerIF;




import java.util.LinkedList;
import java.util.List;

public  class  MenHRManagerimpl implements HRManagerIF{
	public static List<People> list = new LinkedList();
	
	/**
	 * 查找pid是否存在，存在就返回People
	 * @param pid
	 * @return
	 */
	private People find(String pid) {
		System.out.println("执行了find。。。。");
		for(People people : list ) {
			 People pp = people; 
			 if(pp.getPid().equals(pid)) {
				 return pp;
			 }
		}
		return null;
	}

	@Override
	public void add(People p) throws HRMException {
		if (find(p.getPid())!=null) {
			throw new HRMException("输入的编号已存在！！");	
		}
		list.add(p) ;
		System.out.println("添加完成！！");
	}//end
	

	@Override
	public void delete(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("输入的编号不存在，无法删除！！");	
		}
		list.remove(find(pid));
		System.out.println("删除完成！！");
	}//end
		

	@Override
	public void update(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("输入的编号不存在，无法更新！！");	
		}
		
		People pold = find(pid);
		MainUI ui = new MainUI();//调用更新输入界面
		People pnew = ui.common();//更新的信息
		if(find(pnew.getPid())!=null){//检查新信息中的id是否已存在
			throw new HRMException("输入的编号已被占用！！");	
		}
		String jold = pold.getJob();
		String jnew = pnew.getJob();
		System.out.println(jold+":"+jnew+"  "+jold.equals(jnew));
		if(!jold.equals(jnew)){//如果职业不同不能更换
			throw new HRMException("职业不同，不支持更新！！");	
		}
		
		int index = list.indexOf(pold);
		list.set(index, pnew);
		System.out.println("更新完毕！！");
	}



	@Override
	public void show(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("输入的编号不存在，无法显示信息！！");	
		}
		People p= find(pid);
		System.out.println("\n"+p.showInfo()+"\n");
	}

	@Override
	public void showAll() throws HRMException {
		if(list.isEmpty()) {
			throw new HRMException("当前无在册人员！！");
		}
		for(People object : list) {
			People people = object;
			System.out.println("\n"+people.showInfo()+"\n");
		}
	}



}


