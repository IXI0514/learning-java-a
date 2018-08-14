package com.yunsi.work0814;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test1 {
	public static void main(String[] args) {
		List list1 = new ArrayList();
		int a = list1.size();
		System.out.println(a);
		list1.add("b");
		list1.add("b");
		list1.add("b");
		list1.add("汉汉汉-_-||");
		System.out.println(list1);
		List list2 = new ArrayList();
		list2.add("*");
		list2.add("*");
		list2.add("*");
		list2.add("*");
		list1.add(list2);
		System.out.println(list2);
		System.out.println(list1);
		list1.addAll(list2);
		System.out.println(list1);
		list1.addAll(1,list2);
		System.out.println(list1);
		list1.set(0, list2);
		System.out.println(list1);
		list2.remove(0);
		System.out.println(list2);
		list2.clear();
		System.out.println(list2);
		System.out.println(list1);
		System.out.println(list1.get(1));
		System.out.println(list1);
		//list2.remove(0);
		Integer ele = new Integer(8);
		
		test();
		System.out.println("================");
		test2();
		System.out.println("================");
		test3();
		
	}
	
	public static void test() {
		List lst1 = new ArrayList();
		List lst2 = new ArrayList();
		System.out.println(lst1.isEmpty());
		for(int i = 0; i<=10;i++) {
			lst1.add(i);
		}
		System.out.println("lst1:"+lst1);
		System.out.println("Size："+lst1.size());
		lst2.add("*");
		lst2.add("*");
		System.out.println("lst2:"+lst2);
		lst1.add(0, lst2);
		lst1.addAll(1, lst2);
		System.out.println("lst1:"+lst1);
		lst1.set(1, "#");
		System.out.println("lst1:"+lst1);
	}
	public static void test2() {
		List lst1 = new ArrayList();
		for(int i = 0; i<=10;i++) {
			lst1.add(i);//添加
		}
		int ele = (Integer)lst1.get(0);//获取
		for(int i=0;i<lst1.size();i++) {
			int d = (Integer)lst1.get(i);
			System.out.print(d+" ");
		}
		System.out.println();
		for(Object b : lst1) {//使用foreach循环遍历List
			int bb = (Integer)b;
			System.out.print(bb+" ");
		}
		
		lst1.set(0, "!!");//替换
		System.out.println("lst1:"+lst1);
		while(lst1.size()>0) {
			lst1.remove(0);//删除-按下标
		}
		
		
		//lst1.clear();
		System.out.println("Removed:"+lst1);
/*		while(true) {
			try {
				lst1.remove(0);
			} catch (Exception e) {
				System.out.println("list1是空吗？:"+lst1.isEmpty());
				break;
			}
			
		}*/
	}
	public static void test3() {
		List lst1 = new ArrayList();
		for(int i = 0; i<=10;i++) {
			lst1.add(i);
		}
		System.out.println("lst1:"+lst1);
		Iterator ite = lst1.iterator();//iterstor 遍历操作
		System.out.println(ite.hasNext());
		while (ite.hasNext()) {
			Object object = ite.next();
			System.out.print(" "+object);
		}
		
		List list = Arrays.asList("a","a","a","a","b");
		Iterator its = list.iterator();
		System.out.println();
		System.out.println(its.hasNext());
		while(its.hasNext()) {
			Object oo = its.next();
			if("bbb".equals(oo)) {
				its.remove();
				//lst1.remove(ele);//Iterator遍历的时候，发现要删除的这个元素，结果使用lst自带的删除方法来删除//并发修改异常
			}
		}
		System.out.println(list);
	}
}
