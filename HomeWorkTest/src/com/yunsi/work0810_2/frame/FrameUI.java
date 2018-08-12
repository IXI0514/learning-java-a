package com.yunsi.work0810_2.frame;

import java.util.Scanner;

import com.yunsi.work0810.service.IHRManagement;
import com.yunsi.work0810.service.impl.HRManagementImpl;
/**
 * 
 * 	����ϵͳ�Ľ������
 * @author ShenBL
 *
 */
public class FrameUI {
	IHRManagement ihr = new HRManagementImpl();
	IHRManagement ihrm = new HRManagementImpl();
	Scanner sc = new Scanner(System.in);
	
	public void start() {//��ʼ
		ihrm.reset();//���÷�������ȡ�ļ��еĶ���
		outer:while(true) {
			System.out.println("========ѡ������=========");
			System.out.println("=     1.�����Ա��Ϣ              =");
			System.out.println("=     2.ɾ����Ա��Ϣ              =");
			System.out.println("=     3.������Ա��Ϣ              =");
			System.out.println("=     4.��ʾ��Ա��Ϣ              =");
			System.out.println("=     5.��ʾ������Ϣ              =");
			System.out.println("=      6.  ��    ��                    =");
			System.out.println("==========End==========");
			
			int start = sc.nextInt();
			switch (start) {//��������ı����
				case 1:	this.addFrame();break;
				case 2:	this.deleteFrame();break;
				case 3:	this.updateFrame();break;
				case 4:	this.showInfoFrame();break;
				case 5:	this.showAllFrame();break;
				case 6:	ihr.save();break outer;
				default:System.out.println("\n   Error01��������������󣡣�\n");continue outer;
			}	
		}
	}

	public void addFrame() {
		
		System.out.println("========�����Ա��=========");
		System.out.print("=     ��ţ�   ");
		String pid =sc.next();
		System.out.print("=     ������   ");
		String name =sc.next();
		System.out.print("=     ���䣺   ");
		int age =sc.nextInt();
		System.out.print("=     �Ա�   ");
		String sex =sc.next();
		System.out.print("=     ְҵ(t:��ʦ��s:ѧ��)��   ");		
		String job =sc.next();
		System.out.print("=     ��ע��   ");
		String mark=sc.next();
		System.out.println("==========End============");
		int b =ihr.find(pid);//ȷ����Ա����Ƿ���� ����ʱid�����ظ�
		if(b==-1) {
			ihr.add( pid,name,age,sex,job,mark);
		}else {
			System.out.println("\n   Error02������ı�������Ѵ��ڣ���\n");
			this.start();
		}
	}

	public void deleteFrame() {
		System.out.println("========ɾ����Ա��=========");
		System.out.print("=     �����û��ı�ţ�   ");
		String pid =sc.next();
		int b =ihr.find(pid);//ȷ����Ա����Ƿ����
		if(b!=-1) {
			ihr.delete(pid);
		}else {
			System.out.println("\n   Error02������ı�����󣬲����ڣ���\n");
			this.start();
		}
	}

	public void updateFrame() {
		System.out.println("========������Ա��=========");
		System.out.print("=     �����û��ı�ţ�   ");
		String pid =sc.next();
		int b =ihr.find(pid);//ȷ����Ա����Ƿ����
		if(b!=-1) {
			System.out.println("========������Ա��=========");
			System.out.print("=     ������   ");
			String name =sc.next();
			System.out.print("=     ���䣺   ");
			int age =sc.nextInt();
			System.out.print("=     �Ա�   ");
			String sex =sc.next();
			System.out.print("=     ְҵ(t:��ʦ��s:ѧ��)��   ");		
			String job =sc.next();
			System.out.print("=     ��ע��   ");
			String mark=sc.next();
			System.out.println("==========End============");
			ihr.update(pid,name,age,sex,job,mark);
		}else {
			System.out.println("\n   Error02������ı�����󣬲����ڣ���\n");
			this.start();
		}
	}

	public void showInfoFrame() {
		System.out.println("========��ʾ��Ա��Ϣ��=========");
		System.out.print("=     �����û��ı�ţ�   ");
		String pid =sc.next();
		int b =ihr.find(pid);//ȷ����Ա����Ƿ����
		if(b!=-1){
			ihr.showInfo(pid);
		}else {
			System.out.println("\n   Error02������ı�����󣬲����ڣ���\n");
			this.start();
		}
		
	}

	public void showAllFrame() {
		System.out.println("========��ʾ������Ա��=========");
		ihr.showAll();
	}



}
