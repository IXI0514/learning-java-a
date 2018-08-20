package com.yunsi.work0816;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
		test();
		test1();
	}

	private static void test1() {
		System.out.println("========map≈≈–Ú=============");
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		};
		Map< Integer, String>  map= new TreeMap<>(comparator);
		map.put(001, "aaa");
		map.put(003, "bbb");
		map.put(002, "ccc");
		map.put(006, "ddd");
		map.put(004, "eee");
		map.put(005, "fff");
		Set<Integer> key = map.keySet();
		for(int k : key) {
			System.out.print("  "+k);
		}
		
	}

	private static void test() {
		Map< String, String>  map= new HashMap<>();
		map.put("p001", "aaa");
		map.put("p002", "bbb");
		map.put("p003", "ccc");
		map.put("p004", "ddd");
		map.put("p005", "eee");
		map.put("p006", "fff");
		System.out.print("size:");
		System.out.println(map.size());
		System.out.println("====================");
		System.out.println("key");
		Set<String> key = map.keySet();
		for(String k : key) {
			System.out.print("  "+k);
		}
		System.out.println("\nvalue:");
		Collection<String> value =  map.values();
		for(String v:value) {
			System.out.print(" "+v);
		}
		System.out.println("\n====================");
		
		Set<Entry<String, String>> entryset = map.entrySet();
		Iterator<Entry<String, String>> iterator = entryset.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String>  entry = iterator.next();			
			System.out.println(entry.getKey()+"----->"+entry.getValue());
		}
		System.out.println("=====================");
		
		
	}
	
		
}
