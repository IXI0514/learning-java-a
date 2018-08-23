package com.yunsi.work0821;

public class Test_runable  implements Runnable{
	static int num = 10;
	public static void main(String[] args) {
		Test_runable thRunable = new Test_runable();
		new Thread(thRunable).start();
		new Thread(thRunable).start();
		new Thread(thRunable).start();
		
	}

	@Override
	public  void run() {
		// TODO Auto-generated method stub
		while (num>0) {
			num--;
			System.out.println(Thread.currentThread().getName()+"  ÷¥––,µ±«∞num £”‡£∫"+num +"  "+ Thread.currentThread().getState());	
		}
	}
}
