package com.yunsi.work0817_����;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * �������� --��ϰ --����
 * @author ShenBL
 *
 */
public class Demo {
	
	public static void main(String[] args) throws Exception  {
		
		Demo1();	
		Demo2();
		
		Demo4();
		Demo3();
	}

	private static void Demo4() throws  Exception{//��ȡ���췽������������
		// TODO Auto-generated method stub
		Class class1 = Class.forName("com.yunsi.work0817_fanshe.People");
		System.out.println("��ȡ���췽��");
		Constructor[] constructor = class1.getConstructors();
		for (Constructor constructor2 : constructor) {
			System.out.println(constructor2);
		}
		System.out.println("��ȡ���й��췽��");
		Constructor[] constructors3 = class1.getDeclaredConstructors();
		for (Constructor constructor2 : constructors3) {
			System.out.println(constructor2);
		}
		System.out.println("��ȡ�����޲����Ĺ��췽��");
		Constructor con = class1.getDeclaredConstructor();
		System.out.println(con);
		System.out.println("��ȡ�вεĹ��췽��");
		Constructor con2 = class1.getDeclaredConstructor(String.class,String.class,String.class,int.class);
		System.out.println(con2);
		System.out.println("���乹�����");
		System.out.println("�޲���");
		Object object = con.newInstance();
		System.out.println(object);
		System.out.println("�в���");
		Object object2 = con2.newInstance("p001","shen","anhui",23);
		System.out.println(object2);
	}

	private static void Demo3() throws Exception {//��ȡ����
		// TODO Auto-generated method stub
		Class class1 = Class.forName("com.yunsi.work0817_fanshe.People");
		Constructor constructor = class1.getConstructor(String.class,String.class,String.class,int.class);
		Object object = constructor.newInstance("P001","BANG","CHINA",23);//��������
		System.out.println("��ȡ���б�����");//������public���ε�
		Field[] fields =class1.getFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i]);
		}
		System.out.println("��ȡ���б���2");//����
		Field[] fields2 = class1.getDeclaredFields();
		for (Field field : fields2) {
			System.out.println(field);
		}
		
		System.out.println("��ȡ����������ֵ����ֵ");
		Field field = class1.getDeclaredField("pid");
		System.out.println(field);
		field.setAccessible(true);//java.lang.IllegalAccessException
		field.set(object, "p002");
		System.out.println(object);
	}

	private static void Demo2() throws Exception {//��ȡ��Ա����
		Class class1 = Class.forName("com.yunsi.work0817_fanshe.People");
		
	
	}

	private static void Demo1() {
		
	}
}
