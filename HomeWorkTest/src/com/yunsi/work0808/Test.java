package com.yunsi.work0808;

import java.io.File;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		
		System.out.println("ѡ�������ʽ��\n---1.����\n---2.����\n---3.ɾ��");//ѡ���ƻ���ճ��
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		Demo d = new Demo();
		System.out.print(" ����Դ�ļ�Ŀ¼��");//Դ�ļ�Ŀ¼����������ڻᱨ��
		String source=sc.next();
		String target = null;
		if(x!=3) {
			System.out.print(" ����Ŀ��Ŀ¼��");//Ŀ���ļ�Ŀ¼�����û���ļ��лᴴ��
			target=sc.next();
		}
		
		
		switch(x) {
			case 1 : d.start(source,target);
					 System.out.println("---�������---");
					 break;
			case 2 : d.cut(source,target);
					 System.out.println("---�������---");
					 break;
			case 3 : d.delete(source);
					 System.out.println("---ɾ�����---");
					break;
			default :System.out.println("ѡ�����Ϣ���󣡣���");
		}		
	}
		
		
		
}

