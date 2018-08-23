package com.yunsi.work0821;

public class Test_extends  extends Thread{
	private int num = 10; 
	
	/*public void run() {
		while(num>0) {
			num--;
			System.out.println(Thread.currentThread().getName()+"  num:"+num +"  "+ Thread.currentThread().getState() );
		}
	}
	
	public static void main(String[] args) {
		new Test_extends().start();
		new Test_extends().start();
	}*/

	 
	
	 

		public void run() {
	 
			for (int i = 0; i <= 100; i++) {
				if(num>0){
					System.out.println(Thread.currentThread().getName()+"--卖出票：" + num--);
				}
			}
		}
		
		
		public static void main(String[] args) {
			new Test_extends().start();
			new Test_extends().start();
			new Test_extends().start();
		
			//每个线程都独立，不共享资源，每个线程都卖出了10张票，总共卖出了30张。如果真卖票，就有问题了。
		}
	 
	}

	
	

