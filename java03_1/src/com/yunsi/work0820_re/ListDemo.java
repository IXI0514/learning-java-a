package com.yunsi.work0820_re;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ListDemo {
	public static void main(String[] args) {
		demo();
		System.out.println("============");
		demo2();
	}
	//treeset
	private static void demo2() {
		// TODO Auto-generated method stub
		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				
				return o1.compareTo(o2);
			}
		};
		Set<String> set = new TreeSet<String>(comparator);
		set.add("a");
		set.add("d");
		set.add("c");
		set.add("e");//�������ظ�
		set.add("f");
		System.out.println(set);
		set.remove("a");
		set.contains("a");
		Iterator iterator = set.iterator();
		System.out.println("���������");
		while (iterator.hasNext()) {
			System.out.print(iterator.next()+" ");
		}
		
	}
	//set  hashset
	private static void demo() {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("d");
		set.add("c");
		set.add("e");
		set.add("e");
		set.add("f");
		System.out.println(set);//û�д���˳��
		boolean b =set.isEmpty();
		int a = set.size();
		System.out.println("set�Ƿ�Ϊ�գ�"+b+"\n��С��"+a);
		set.remove("c");//ɾ��
		System.out.println(set);
		String[] strs =set.toArray(new String[0] );
		boolean  b2= strs[1] instanceof String;	
		System.out.println(strs[1]);
		boolean c = set.contains("a");
		System.out.println("����a����"+c);
	}
	
}
