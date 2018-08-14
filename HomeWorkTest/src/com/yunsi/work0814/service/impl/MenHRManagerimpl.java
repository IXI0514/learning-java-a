package com.yunsi.work0814.service.impl;


import com.yunsi.work0814.beans.People;
import com.yunsi.work0814.exception.HRMException;
import com.yunsi.work0814.frame.MainUI;
import com.yunsi.work0814.service.HRManagerIF;




import java.util.LinkedList;
import java.util.List;

public  class  MenHRManagerimpl implements HRManagerIF{
	public static List list = new LinkedList();
	
	/**
	 * ����pid�Ƿ���ڣ����ھͷ���People
	 * @param pid
	 * @return
	 */
	private People find(String pid) {
		for(Object people : list ) {
			 People pp = (People)people; 
			 if(pp.getPid().equals(pid)) {
				 return pp;
			 }
		}
		return null;
	}

	@Override
	public void add(People p) throws HRMException {
		if (find(p.getPid())!=null) {
			throw new HRMException("����ı���Ѵ��ڣ���");	
		}
		list.add(p) ;
		System.out.println("�����ɣ���");
	}//end
	

	@Override
	public void delete(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("����ı�Ų����ڣ��޷�ɾ������");	
		}
		list.remove(find(pid));
		System.out.println("ɾ����ɣ���");
	}//end
		

	@Override
	public void update(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("����ı�Ų����ڣ��޷����£���");	
		}
		
		People pold = (People)find(pid);
		MainUI ui = new MainUI();//���ø����������
		People pnew = ui.common();//���µ���Ϣ
		if(find(pnew.getPid())!=null){//�������Ϣ�е�id�Ƿ��Ѵ���
			throw new HRMException("����ı���ѱ�ռ�ã���");	
		}
		String jold = pold.getJob();
		String jnew = pnew.getJob();
		System.out.println(jold+":"+jnew+"  "+jold.equals(jnew));
		if(!jold.equals(jnew)){//���ְҵ��ͬ���ܸ���
			throw new HRMException("ְҵ��ͬ����֧�ָ��£���");	
		}
		
		int index = list.indexOf(pold);
		list.set(index, pnew);
		System.out.println("������ϣ���");
	}



	@Override
	public void show(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("����ı�Ų����ڣ��޷���ʾ��Ϣ����");	
		}
		People p= (People)find(pid);
		System.out.println("\n"+p.showInfo()+"\n");
	}

	@Override
	public void showAll() throws HRMException {
		if(list.isEmpty()) {
			throw new HRMException("��ǰ���ڲ���Ա����");
		}
		for(Object object : list) {
			People people = (People)object;
			System.out.println("\n"+people.showInfo()+"\n");
		}
	}



}


