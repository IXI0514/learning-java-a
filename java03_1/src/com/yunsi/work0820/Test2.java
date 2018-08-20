package com.yunsi.work0820;
/**
 * 
 * Thred  Ù–‘
 * @author ShenBL
 *
 */
class C extends Thread{
	public C (String name) {
		super(name);
	}
}



public class Test2 {
	public static void main(String[] args) {
		C  c =new C("p001");
		System.out.println(c.getName());
		c.setName("p002");
		c.setPriority(Thread.MAX_PRIORITY);
		c.setPriority(Thread.MIN_PRIORITY);
		c.setPriority(Thread.NORM_PRIORITY);
		c.getPriority();

		System.out.println(c);
	}
}
