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
	
	public static int DEFAULT_LEN=10;//Ĭ�Ͽ��Դ�10��
	public static People[] person; //DiskManagerҪ����
	public static int idx =0;//��¼�Ѵ������
	
	
	@Override
	public void reset() {
		People[] temp = idisk.readDate();
		if (temp!=null) {
			person = temp;
			idx = temp.length;
			//System.out.println(temp.length+"  " + person.length+" "+person[0].getPid());//check
		}else {
			new HRManagementImpl(DEFAULT_LEN);
		}	
	}
	public void save() {
		idisk.writeData(person, idx);
	}
	
	public HRManagementImpl() {
	}
	public HRManagementImpl(int len) {
		person = new People[len];//�Զ��峤��
	}
	
	
	
	
	@Override
	public void add(String pid, String name, int age, String sex, String job, String mark) {
		if(idx>=person.length) {//��������
			DEFAULT_LEN*=2;
			People[] men=Arrays.copyOf(person, DEFAULT_LEN);
			person = men;
			//System.out.println("����������,��ǰ���ȣ� "+DEFAULT_LEN);//check
		}
		try {
			if(job.equalsIgnoreCase("s")) {
				Student s =  new Student(pid,name,age,sex,mark);
				person[idx] = s;
				System.out.println("\n    --���ѧ���ɹ�!!--");
			}else if(job.equalsIgnoreCase("t")) {
				Teacher t = new Teacher(pid, name, age, sex, mark);
				person[idx] = t;
				System.out.println("\n    --��ӽ�ʦ�ɹ�!!--");
			}else {
				throw new RuntimeErrorException(null);//ְҵ��������ȷ	
			}
		} catch (RuntimeErrorException e) {
			System.out.println("����ʧ�ܣ�ְҵ�������󣡣�");
			FrameUI fram = new FrameUI();
			fram.start();
		} 
		idx++; 
	}
	
	@Override
	public void delete(String pid) {
		for (int i=0;i<idx;i++){
		      if (person[i].getPid().equals(pid)){
		        for(int j=i;i<idx-1;i++){//����ɾ��λ�ú���ĳ�Ա��ǰ�ƶ�
		          person[j]=person[j+1];
		        }
		        System.out.println("\n      --ɾ���ɹ�!!--");
		        break;
		      }
		 }//end for1
		idx--;//��Ա����
	}//end
		

	@Override
	public void update(String pid, String name, int age, String sex, String job, String mark) {
		int index = find(pid);
//		if(!(person[index].getJob().equalsIgnoreCase(job))) {
//			System.out.println("ְҵ��ͬ���ܸ���");
//			FrameUI fram = new FrameUI();
//			fram.start();
//		}
		try {
			if (!(person[index].getJob().equalsIgnoreCase(job))) {
				throw new HRMException();//ְҵ��ͬ�ͱ���
			}else if(job.equalsIgnoreCase("s")) {
				Student s =  new Student(pid,name,age,sex,mark);
				person[index] = s ;
			}else if(job.equalsIgnoreCase("t")) {
				Teacher t = new Teacher(pid, name, age, sex, mark);
				person[index] = t ;
			}
		} catch (HRMException e) {
			System.out.println(" ����ʧ�ܣ�ְҵ�������󣡣�");
			FrameUI fram = new FrameUI();
			fram.start();
		} 
		System.out.println("\n      --���³ɹ�!!--");		
	}

	

	
	public int find(String pid) {//Ϊdelete update  showInfo�ṩѰ�ҷ���
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
			 System.out.print("    [ ���: "+p.getPid()+"  ����: "+ p.getName()+"  �Ա�: "+ p.getSex()+" ����: "+p.getAge()+" ְҵ: "+p.getJob());
			 p.showMessage(); 
		 }    
	}
	
	@Override
	public void showAll() {
		if(idx>0) {
			for(int i=0;i<idx;i++) {
				People p =person[i];
				System.out.print("\n    [ ���: "+p.getPid()+"  ����: "+ p.getName()+"  �Ա�: "+ p.getSex()+" ����: "+p.getAge()+" ְҵ: "+p.getJob());
				p.showMessage();
			
			}
		}else {
			System.out.println("\n  Ŀǰû���ڲ���Ա��");
		}
		
	}
	
	


	


}
