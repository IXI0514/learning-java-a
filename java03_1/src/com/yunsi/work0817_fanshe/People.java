package com.yunsi.work0817_fanshe;

/**
 * 
 * �������� --��ϰ --������
 * @author ShenBL
 *
 */

public class People {
	
	
	//�������η����εı���
	private String pid; 
	protected String name;
	public String home;
	int age;
	
	//���췽��
	public People() {}

	
	public People(String pid, String name, String home, int age) {
		super();
		this.pid = pid;
		this.name = name;
		this.home = home;
		this.age = age;
	}


	@Override
	public String toString() {
		return "People [pid=" + pid + ", name=" + name + ", home=" + home + ", age=" + age + "]";
	}

	private  People(String pid, String name) {
		this.pid = pid;
		this.name = name;
	}
	
	//��ͨ����
	public void test1() {
		System.out.println("�޲����޷���ֵ-public����");
	}
	private void test2() {
		System.out.println("�޲����޷���ֵ-private����");
	}
	
	
	public void test3(String str) {
		System.out.println("�в����޷���ֵ-public����-��"+str);
	}
	private void test30(String str) {
		System.out.println("�в����޷���ֵ-private����-��"+str);
	}
	public void test4(String str,int i) {
		System.out.println("��������޷���ֵ-public���� ��"+str+"-----"+i);
	}
	
	public String test5(String str) {
		str = "�в����з���ֵ-public����-";
		return str;
	}
	private String test6(int i) {
		String str="�ַ���ֵ�в��� private���Σ�";
		for(int j=0;j<=i;j++) {
			 str+=("ѭ��"+j+";");
		}		
		return str;
	}
	
	
} 
