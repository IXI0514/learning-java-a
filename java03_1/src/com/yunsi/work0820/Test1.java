package com.yunsi.work0820;

class A extends Thread{

	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println("a�е�ǰ�߳� :"+thread);
	}
	
}
class B implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("b��ǰ�߳� "+Thread.currentThread());
	}
	
}

public class Test1 {

	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println("Run�е�ǰ�߳� :"+thread);
	}
	
	public static void main(String[] args) {
		Thread thread = Thread.currentThread();
		System.out.println(thread);
		thread.getName();
		A a = new A();
		a.start();
		B b = new B();
		b.run();
	}
}
