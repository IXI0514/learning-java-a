package com.yunsi.work0814.frame;

import java.util.Scanner;

import com.yunsi.work0814.beans.People;
import com.yunsi.work0814.beans.sub.Athlete;
import com.yunsi.work0814.beans.sub.Doctor;
import com.yunsi.work0814.beans.sub.Teacher;
import com.yunsi.work0814.exception.HRMException;
import com.yunsi.work0814.service.HRManagerIF;
import com.yunsi.work0814.service.factory.HRMnagerFactory;


                        

public class MainUI {
	
	
	Scanner sc = new Scanner(System.in);
	private HRManagerIF ihr;
	public MainUI()  {
		ihr = HRMnagerFactory.autofactory();
	}
	public void start() {//��ʼ
		outer:while(true) {
			System.out.println("\n========ѡ������======");
			System.out.println("=     1.�����Ա��Ϣ      =");
			System.out.println("=     2.ɾ����Ա��Ϣ      =");
			System.out.println("=     3.������Ա��Ϣ      =");
			System.out.println("=     4.��ʾ��Ա��Ϣ      =");
			System.out.println("=     5.��ʾ������Ϣ      =");
			System.out.println("=     6.��         ��           =");
			System.out.println("==========End========\n");
			int start = sc.nextInt();
			switch (start) {//��������ı����
				case 1:	this.addFrame();break;
				case 2:	this.deleteFrame();break;
				case 3:	this.updateFrame();break;
				case 4:	this.showInfoFrame();break;
				case 5:	this.showAllFrame();break;
				case 6:	break outer;
				default:System.out.println("\n   Error01��������������󣡣�\n");continue outer;
			}	
		}
	}
	//add update ���еĽ���
	public People common() {
		System.out.print("=     ��ţ�   ");
		String pid =sc.next();
		System.out.print("=     ������   ");
		String name =sc.next();
		System.out.print("=     ���䣺   ");
		int age =sc.nextInt();
		System.out.print("=     �Ա�   ");
		String sex =sc.next();
		System.out.println("=     ְҵ(a���˶�Ա/d:ҽ��/t����ʦ)��   ");		
		String job =sc.next();
		People people = null;
		switch (job) {
		case "A":case "a":{
			System.out.print("�˶���Ŀ��");
			String sport = sc.next();
			Athlete a = new Athlete(pid, name, sex, age, "�˶�Ա", sport);
			people = a;
			
		}break;
		case "D":case "d":{
			System.out.println("ҽԺ��");
			String hospital =  sc.next();
			System.out.println("��Ŀ��");
			String dtype = sc.next();
			Doctor d = new Doctor( pid,  name,  sex,  age,  "ҽ��",  hospital, dtype) ;
			people = d;
		}break;
		case "T":case "t":{
			System.out.println("ѧУ��");
			String school =  sc.next();
			System.out.println("��Ŀ��");
			String course = sc.next();
			Teacher t = new Teacher( pid,  name,  sex,  age,  "��ʦ",  school, course) ;
			people = t;
		}break;
		case "" :default:System.out.println("ְҵҪ��Ҫ�����룡��");this.start() ;
		}
		return people;
	}
	
	public void addFrame()  {
		System.out.println("========�����Ա��=========");
		People people =this.common();
		try {
			ihr.add(people);
		} catch (HRMException e) {
			System.out.println(e);
			this.start();
		}
		System.out.println("==========End============");
	}

	public void deleteFrame()  {
		System.out.println("========ɾ����Ա��=========");
		System.out.print("=     �����û��ı�ţ�   ");
		String pid =sc.next();
		try {
			ihr.delete(pid);
		} catch (HRMException e) {
			System.out.println(e);
			this.start();
		}
	}

	public void updateFrame() {
		System.out.println("========������Ա��=========");
		System.out.print("=     �����û��ı�ţ�   ");
		String pid =sc.next();
		try {
			ihr.update(pid);
		} catch (HRMException e) {
			System.out.println(e);
			this.start();
		}
		
	}

	public void showInfoFrame() {
		System.out.println("========��ʾ��Ա��Ϣ��=========");
		System.out.print("=     �����û��ı�ţ�   ");
		String pid =sc.next();
		try {
			ihr.show(pid);
		} catch (HRMException e) {
			System.out.println(e);
			this.start();
		}
	}

	public void showAllFrame() {
		System.out.println("========��ʾ������Ա��=========");
		try {
			ihr.showAll();
		} catch (HRMException e) {
			System.out.println(e);
			this.start();
		}
	}



}