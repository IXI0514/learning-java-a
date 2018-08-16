package com.yunsi.work0815;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {
		//test();
		test1();
	}
	public static void test() {//复习
		List<String> list = new ArrayList<String>() ;
		for(int i= 0; i<10;i++) {
			list.add(String.valueOf(i));
		}
		list.add(0,"#");
		System.out.println(list);
		Iterator<String> iterator  = list.iterator();
		System.out.println("迭代输出：");
		while(iterator.hasNext()) {
			System.out.print("  "+iterator.next());
		}
		boolean  b = list.contains("#");
		System.out.println("\n是否存在#:"+b);
		if(b) {
			System.out.println("#-->*");
			list.set(list.indexOf("#"), "*");
		}
		System.out.println(list);
		while(list.size()>0) {
			list.remove(0);
		}
		System.out.println("\n目前：\n"+list);
	}
	
	public static void test1() {
		System.out.println("========HashSet========");
		Set<String> list = new HashSet<>();
		for(int i= 0; i<10;i++) {
			list.add(String.valueOf(i));
		}
		list.add("#");
		list.add("*");
		System.out.println("直接输出:\n"+list);
		List<String> op = new ArrayList<>(Arrays.asList("*","#"));
		if(list.containsAll(op)) {
			list.remove("#");
		}
		System.out.println("迭代输出：");
		Iterator<String> iterator= list.iterator();
		while(iterator.hasNext()) {
			
			System.out.print(" "+iterator.next());
		}
		
		list.clear();
		System.out.println("\nlist 被清空？  "+list.isEmpty());
		
		
		System.out.println("\n======TreeSet=======");
		Set<Integer> list2 = new TreeSet<Integer>();
		for(int i= 0; i<=10;i++) {
			list2.add(i);
		}
	
		list2.add(11);
		list2.add(11);
		list2.add(11);
		System.out.println("直接输出:\n"+list2);
		System.out.println("迭代输出：");
		Iterator<Integer> iterator2= list2.iterator();
		while(iterator2.hasNext()) {
			System.out.print(" "+iterator2.next());
		}
		
	}
	
	
}
