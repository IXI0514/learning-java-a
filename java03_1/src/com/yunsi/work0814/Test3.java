package com.yunsi.work0814;
    
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Test3 {
	public static final int NUM=300000;
	public static void main(String[] args) {
		System.out.println("234");
		test1();
		test2();
		test3();
	}
	public static void test1() {
		List list = new LinkedList();
		long s = System.currentTimeMillis();
		for ( int i = 0; i < NUM; i++) {
			list.add(0,i);
		}
		long e = System.currentTimeMillis();
		System.out.println("LinkedLIst "+ (e-s)+"ms");
	}
	public static void test2() {
		List list = new ArrayList();
		long s = System.currentTimeMillis();
		for ( int i = 0; i < NUM; i++) {
			list.add(0,i);
		}
		long e = System.currentTimeMillis();
		System.out.println("LinkedLIst "+ (e-s)+"ms");
	}
	public static void test3() {
		List list = new Vector();
		long s = System.currentTimeMillis();
		for ( int i = 0; i < NUM; i++) {
			list.add(0,i);
		}
		long e = System.currentTimeMillis();
		System.out.println("LinkedLIst "+ (e-s)+"ms");
	}
	
}

