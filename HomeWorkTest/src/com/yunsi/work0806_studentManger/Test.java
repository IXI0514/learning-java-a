package com.yunsi.work0806_studentManger;

import com.yunsi.work0806_studentManger.service.IHRManager;
import com.yunsi.work0806_studentManger.service.impl.*;
import com.yunsi.work0806_studentManger.stu.*;;

public class Test {
	public static void main(String[] args) {
		
		Student s1 =new Student("001", "��", "��", 23, "x");
		Student s2 =new Student("002", "Ǯ", "��", 23, "x");
		Student s3 =new Student("003", "��", "��", 69, "x");
		Teacher t1 =new Teacher("004", "��", "��", 54, "x");
		IHRManager hr = new HRManagerimpl();
		System.out.println("\n--���:\n");
		hr.add(s1);
		hr.add(s2);
		hr.add(s3);
		hr.add(t1);
		
		//System.out.println("\n--����:\n");
		//hr.find("001");
		
		//System.out.println("\n--ɾ��:\n");
		//hr.delete(s1);
		
		//System.out.println("\n--����:\n");
		//Student s4 =new Student("003", "��", "Ů", 32, "w");
		//hr.update(s4);
		
		
		hr.show();
		
		System.out.println("\n-----End-----\n");
	}
}
