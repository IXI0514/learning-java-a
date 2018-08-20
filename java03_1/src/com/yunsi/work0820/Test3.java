package com.yunsi.work0820;
/**
 * 线程休眠  与多线程
 * @author ShenBL
 *
 */
public class Test3 {
	public static void main(String[] args) {
		demo();
	}

	private static void demo() {
		// TODO Auto-generated method stub
		/*for (int i = 0; i < 24; i++) {
			System.out.println(Thread.currentThread()+" : "+ i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}*/
		Thread thread = Thread.currentThread();
		System.out.println("当前线程："+thread+"\n");
		
		thread1();
		System.out.println("============");
		thread2();
	}
	
	private static void thread2() {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				for (int i = 0; i < 24; i++) {
					System.out.println(Thread.currentThread()+" : "+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private static void thread1() {
		// TODO Auto-generated method stub
		Thread thread = new Thread() {
			public void run() {
				for (char i = 'a'; i < 'z'; i++) {
					System.out.println(Thread.currentThread()+" : "+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
		thread.setPriority(Thread.MAX_PRIORITY);
	}

	
	
}
