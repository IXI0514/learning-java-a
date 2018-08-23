package com.yunsi.work0821;

public class Test2 {
	public static void main(String[] args) {
		//new Thread().start();
		new Thread() {
			public void run() {
				for (int i=0;i<1000;i++) {
					if (i%3==0) {
						System.out.println(getName()+"ÈÃ");
						Thread.yield();
					}
					System.out.println(getName()+"  "+i);
				}
			}
		}.start();
		new Thread() {
			public void run() {
				
				System.out.println("²»¸É"+getName()+"ÈÃ");
				Thread.yield();

			}
		}.start();
	}
}
