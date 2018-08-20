package com.yunsi.work0820;

class A extends Thread{

	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println("a中当前线程 :"+thread);
	}
	
}
class B implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("b当前线程 "+Thread.currentThread());
	}
	
}

public class Test1 {

	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println("Run中当前线程 :"+thread);
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
