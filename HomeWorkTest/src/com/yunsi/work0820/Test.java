package com.yunsi.work0820;

/**
 * Test
 * @author ShenBL
 *
 */
public class Test {
	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		System.out.println("main������ִ���߳��ǣ�"+t.getName());
		MyFrame frame = new MyFrame();
	}
	
}
