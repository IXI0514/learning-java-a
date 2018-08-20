package com.yunsi.work0820_re;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapDemo {
	public static void main(String[] args) {
		temo();
	}
	//map
	private static void temo() {
		// TODO Auto-generated method stub
		Comparator<String> comparator= new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		Map< String , String> map = new TreeMap<String,String>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
		map.put("111", "val1");
		map.put("222", "val2");
		map.put("222", "val2");//≤ªø…÷ÿ∏¥
		map.put("333", "val3");
		map.put("555", "val4");
		System.out.println(map);
		System.out.println(map.size());
		System.out.print("key: ");
		Set<String> set = map.keySet();
		System.out.println(set);
		Collection<String> collection = map.values();
		System.out.print("values:");
		System.out.println(collection);
		
		
	}
}
