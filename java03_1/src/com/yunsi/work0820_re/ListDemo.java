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
		set.add("e");//不可以重复
		set.add("f");
		System.out.println(set);
		set.remove("a");
		set.contains("a");
		Iterator iterator = set.iterator();
		System.out.println("迭代输出：");
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
		System.out.println(set);//没有存入顺序
		boolean b =set.isEmpty();
		int a = set.size();
		System.out.println("set是否为空："+b+"\n大小："+a);
		set.remove("c");//删除
		System.out.println(set);
		String[] strs =set.toArray(new String[0] );
		boolean  b2= strs[1] instanceof String;	
		System.out.println(strs[1]);
		boolean c = set.contains("a");
		System.out.println("包含a：？"+c);
	}
	
}
