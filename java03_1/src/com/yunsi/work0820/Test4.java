package com.yunsi.work0820;

public class Test4 {
	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		Thread thread = new Thread() {
			public void run() {
				for (int i = 0; i < 10&&!this.isInterrupted(); i++) {
					System.out.println("  "+ i);
					
					try {
						System.out.println("休眠1s");
						
						Thread.sleep(1000);
						System.out.println("===");
					} catch (InterruptedException e) {
						this.interrupt();
						//e.printStackTrace();
						
					}
				}
			}
		};
		System.out.println(thread);
		
		thread.start();
		System.out.println("main启用 thread线程 自己休眠5s");
		delay(5);
		
	
		System.out.println("线程中断");
		thread.interrupt();
		System.out.println(thread.isInterrupted());
	}
	public static void delay(int i){
		long mark = System.currentTimeMillis();
		while (System.currentTimeMillis()-mark <i*1000);
	}
}
