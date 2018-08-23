package com.yunsi.work0822_多线程2;

import java.util.Iterator;

import javax.print.attribute.standard.Chromaticity;

public class Test1 {
	private int num = 10;
	public static void main(String[] args) {
		Test1 test1 = new Test1();
		/*new Thread() {
			public void run() {		
				test1.print("1232131231313");
			}
		}.start();
		
		new Thread() {
			public void run() {
				test1.print("asdasdb");
			}		
		}.start();*/
		
		//static
		new Thread() {
			public void run() {		
				dosyn();
			}
		}.start();
		
		new Thread() {
			public void run() {
				dosyn();
			}		
		}.start();
		
	}

	
	public synchronized static  void dosyn() {
		for (int i=0;i<10;i++) {
			System.out.println(i +Thread.currentThread().getName() +" : static synchronized.....");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void dosth() {
		Class class1 = Test1.class;
			synchronized (class1) {
				for (int i=0;i<10;i++) {
					System.out.println(i + Thread.currentThread().getName()+" : static synchronized (class1) .....");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
	}
	public static void dos() {
		for (int i = 0; i <5; i++) {
			System.out.println(i + Thread.currentThread().getName()+" : static .....");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private   void print(String s) {
		System.out.println("this:"+this);
		synchronized(this) {
			char[] ch = s.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				System.out.print(ch[i]);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("    开始装订");
		
	}
	/*private synchronized static void print(String s) {
		// TODO Auto-generated method stub
		char[] ch = s.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			System.out.print(ch[i]);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}*/
}
