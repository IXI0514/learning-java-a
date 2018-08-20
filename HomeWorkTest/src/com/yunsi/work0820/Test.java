package com.yunsi.work0820;

/**
 * Test
 * @author ShenBL
 *
 */
public class Test {
	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		System.out.println("main方法中执行线程是："+t.getName());
		MyFrame frame = new MyFrame();
	}
	
}
