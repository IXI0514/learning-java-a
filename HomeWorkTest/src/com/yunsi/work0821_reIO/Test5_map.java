package com.yunsi.work0821_reIO;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;



import java.util.Set;
import java.util.TreeMap;



public class Test5_map {
	public static void main(String[] args) {
		//demo();
		demo2();
	}

	private static void demo2() {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<String>();
		set.add("111");
		set.add("222");
		set.add("333");
		set.add("444");
		System.out.println(set);
	}

	private static void demo() {
		// TODO Auto-generated method stub
		People people1 = new People("p001","shen");
		People people2 = new People("p002","bang");
		People people3 = new People("p003","ren");
		Map<People, String> map = new TreeMap<>();
		map.put(people1, "123");
		map.put(people2, "123");
		map.put(people3, "123");
		
		Set<Entry<People, String>> setmap= map.entrySet();
		Iterator<Entry<People, String>> iterator = setmap.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		Set<People> set =map.keySet();
		
		System.out.println(set);
		
	}
}
