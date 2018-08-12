package com.yunsi.work0810.service.impl;

import com.yunsi.work0810.beans.People;
import com.yunsi.work0810.frame.FrameUI;
import com.yunsi.work0810.service.IDiskHRManagement;
import com.yunsi.work0810.service.IHRManagement;
import java.util.Arrays;

import javax.management.RuntimeErrorException;

import com.yunsi.work0810.HRMException.HRMException;
import com.yunsi.work0810.beans.*;

public  class HRManagementImpl implements IHRManagement{
	
	IDiskHRManagement idisk = new DiskHRManagementImpl();
	
	public static int DEFAULT_LEN=10;//默认可以存10人
	public static People[] person; //DiskManager要调用
	public static int idx =0;//记录已存的人数
	
	
	@Override
	public void reset() {
		People[] temp = idisk.readDate();
		if (temp!=null) {
			person = temp;
			idx = temp.length;
			//System.out.println(temp.length+"  " + person.length+" "+person[0].getPid());//check
		}else {
			HRManagementImpl hrmimpl = new HRManagementImpl(DEFAULT_LEN);
		}	
	}
	public void save() {
		idisk.writeData(person, idx);
	}
	
	public HRManagementImpl() {
	}
	public HRManagementImpl(int len) {
		person = new People[len];//自定义长度
	}
	
	
	
	
	@Override
	public void add(String pid, String name, int age, String sex, String job, String mark) {
		if(idx>=person.length) {//数组扩容
			DEFAULT_LEN*=2;
			People[] men=Arrays.copyOf(person, DEFAULT_LEN);
			person = men;
			//System.out.println("数组已扩容,当前长度： "+DEFAULT_LEN);//check
		}
		try {
			if(job.equalsIgnoreCase("s")) {
				Student s =  new Student(pid,name,age,sex,mark);
				person[idx] = s;
				System.out.println("\n    --添加学生成功!!--");
			}else if(job.equalsIgnoreCase("t")) {
				Teacher t = new Teacher(pid, name, age, sex, mark);
				person[idx] = t;
				System.out.println("\n    --添加教师成功!!--");
			}else {
				throw new RuntimeErrorException(null);//职业参数不正确	
			}
		} catch (RuntimeErrorException e) {
			System.out.println("创建失败！职业参数错误！！");
			FrameUI fram = new FrameUI();
			fram.start();
		} 
		idx++; 
	}
	
	@Override
	public void delete(String pid) {
		for (int i=0;i<idx;i++){
		      if (person[i].getPid().equals(pid)){
		        for(int j=i;i<idx-1;i++){//将被删除位置后面的成员向前移动
		          person[j]=person[j+1];
		        }
		        System.out.println("\n      --删除成功!!--");
		        break;
		      }
		 }//end for1
		idx--;//成员减少
	}//end
		

	@Override
	public void update(String pid, String name, int age, String sex, String job, String mark) {
		int index = find(pid);
//		if(!(person[index].getJob().equalsIgnoreCase(job))) {
//			System.out.println("职业不同不能更新");
//			FrameUI fram = new FrameUI();
//			fram.start();
//		}
		try {
			if (!(person[index].getJob().equalsIgnoreCase(job))) {
				throw new HRMException();//职业不同就报错
			}else if(job.equalsIgnoreCase("s")) {
				Student s =  new Student(pid,name,age,sex,mark);
				person[index] = s ;
			}else if(job.equalsIgnoreCase("t")) {
				Teacher t = new Teacher(pid, name, age, sex, mark);
				person[index] = t ;
			}
		} catch (HRMException e) {
			System.out.println(" 创建失败！职业参数错误！！");
			FrameUI fram = new FrameUI();
			fram.start();
		} 
		System.out.println("\n      --更新成功!!--");		
	}

	

	
	public int find(String pid) {//为delete update  showInfo提供寻找方法
		for(int i=0;i<idx;i++) {
			//System.out.println(person[0].getPid());
			if((person[i].getPid()).equals(pid)) {
				return i;
			}
		}
		return -1;
	}//end find
	
	public void showInfo(String pid) {
		this.showInfo(person[find(pid)]);
	}
	@Override
	public void showInfo(People p) {
	
		 if (p!=null) {
			 //System.out.println(p.getJob());
			 System.out.print("    [ 编号: "+p.getPid()+"  名字: "+ p.getName()+"  性别: "+ p.getSex()+" 年龄: "+p.getAge()+" 职业: "+p.getJob());
			 p.showMessage(); 
		 }    
	}
	
	@Override
	public void showAll() {
		if(idx>0) {
			for(int i=0;i<idx;i++) {
				People p =person[i];
				System.out.print("\n    [ 编号: "+p.getPid()+"  名字: "+ p.getName()+"  性别: "+ p.getSex()+" 年龄: "+p.getAge()+" 职业: "+p.getJob());
				p.showMessage();
			
			}
		}else {
			System.out.println("\n  目前没有在册人员！");
		}
		
	}
	
	


	


}
