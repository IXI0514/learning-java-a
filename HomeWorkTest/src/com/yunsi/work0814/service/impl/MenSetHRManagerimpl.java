package com.yunsi.work0814.service.impl;
import com.yunsi.work0814.beans.People;
import com.yunsi.work0814.exception.HRMException;
import com.yunsi.work0814.frame.MainUI;
import com.yunsi.work0814.service.HRManagerIF;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *	�����ڴ�
 * 	Set����ʵ�ֵ�
 * @author ShenBL
 *
 */
public class MenSetHRManagerimpl implements HRManagerIF {

	public static Set<People> peoples = new TreeSet<>();
	private People find(String pid) {//�ṩ���ҷ���
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
	
	
	//���
	@Override
	public void add(People p) throws HRMException {
		if (find(p.getPid())!=null) {
			throw new HRMException("����ı���Ѵ��ڣ���");	
		}
		peoples.add(p);	
		System.out.println("�����ɣ���");
	}
	//���
	@Override
	public void delete(String pid) throws HRMException {
		People people = find(pid);
		if(people==null){
			throw new HRMException("����ı�Ų����ڣ��޷�ɾ������");	
		}
		peoples.remove(people);
		System.out.println("ɾ����ɣ���");	
	}
	//����
	@Override
	public void update(String pid) throws HRMException {
		People people = find(pid);
		if(people==null){
			throw new HRMException("����ı�Ų����ڣ��޷����£���");	
		}
		peoples.remove(people);
		MainUI ui = new MainUI();//���ø����������
		People pnew = ui.common();//���µ���Ϣ
		this.add(pnew);
		
	}
	//��ʾ��Ϣ
	@Override
	public void show(String pid) throws HRMException {
		People people = find(pid);
		if(people==null){
			throw new HRMException("����ı�Ų����ڣ��޷���ʾ��Ϣ����");	
		}
		System.out.println("\n"+people.showInfo()+"\n");
		
	}
	//��ʾ����
	@Override
	public void showAll() throws HRMException {
		if(peoples.isEmpty()) {
			throw new HRMException("��ǰ���ڲ���Ա����");
		}
		Iterator<People> iterator = peoples.iterator();
		while ( iterator.hasNext()) {
			People people= iterator.next();
			System.out.println("\n"+people.showInfo()+"\n");
		}
		
	}
	


}
