package com.yunsi.work0806_studentManger;

import com.yunsi.work0806_studentManger.service.IHRManager;
import com.yunsi.work0806_studentManger.service.impl.*;
import com.yunsi.work0806_studentManger.stu.*;;

public class Test {
	public static void main(String[] args) {
		
		Student s1 =new Student("001", "ÕÔ", "ÄĞ", 23, "x");
		Student s2 =new Student("002", "Ç®", "ÄĞ", 23, "x");
		Student s3 =new Student("003", "Ëï", "ÄĞ", 69, "x");
		Teacher t1 =new Teacher("004", "Ëï", "ÄĞ", 54, "x");
		IHRManager hr = new HRManagerimpl();
		System.out.println("\n--Ìí¼Ó:\n");
		hr.add(s1);
		hr.add(s2);
		hr.add(s3);
		hr.add(t1);
		
		//System.out.println("\n--²éÕÒ:\n");
		//hr.find("001");
		
		//System.out.println("\n--É¾³ı:\n");
		//hr.delete(s1);
		
		//System.out.println("\n--¸üĞÂ:\n");
		//Student s4 =new Student("003", "ÖÜ", "Å®", 32, "w");
		//hr.update(s4);
		
		
		hr.show();
		
		System.out.println("\n-----End-----\n");
	}
}
