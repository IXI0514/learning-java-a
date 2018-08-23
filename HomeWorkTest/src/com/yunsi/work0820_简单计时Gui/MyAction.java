package com.yunsi.work0820_简单计时Gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;




/**
 * 
 * 计时的方法
 * @author ShenBL
 *
 */
public class MyAction {
	private static Thread thread1;
	private static Thread thread2;
	private static String tipStr;
	private static String printStr;
	private static long startt;
	

	
	
	public void CurrDate() {
		
		 thread1 =new Thread() {
			public void run() {
				while (true) {
					Date d = new Date();
					SimpleDateFormat sim =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateStr = sim.format(d);
					MyFrame.date.setText("当前时间："+dateStr);//当前时间
	
				}
			}
		};
		thread1.setName("CurrDate_thread");
		thread1.start();
		System.out.println("当前时间的进程："+thread1.getName());
		
	}
	
	
	
	//开始计时
	public void start(long start) {//获取时间并体现在fram上
		startt = start;
		thread2 = new Thread() {
			public void run() {
				while (!thread2.isInterrupted()) {
					long now = System.currentTimeMillis();
					SimpleDateFormat sim= new SimpleDateFormat("mm:ss");
					String str0 = sim.format(now-start);
					 tipStr = String.valueOf(str0);
					MyFrame.tlab.setText("计时：" + tipStr);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						this.interrupt();
					}
				}
				/*while (true) {
					if (!thread2.isInterrupted()) {
						long now = System.currentTimeMillis();
						SimpleDateFormat sim= new SimpleDateFormat("mm:ss");
						String str0 = sim.format(now-start);
						 tipStr = String.valueOf(str0);
						MyFrame.tlab.setText("计时：" + tipStr);
					}else {
						System.out.println("中断中。。。");
					}
				}*/
			}
		};
		thread2.setName("Time_thread");
		thread2.start();
		System.out.println("计时进程："+thread2.getName());
	}	
	//记次 //按下后记录当前时间添加到列表中
	public void print() {
		// TODO Auto-generated method stub
		
		if (printStr!=null) {
			printStr = printStr+"---"+tipStr;
			MyFrame.tare.setText(printStr);
		}else {
			printStr = tipStr;
			MyFrame.tare.setText(tipStr);
		}
		
	}
		
	//暂停//暂停计时
	public void stop() {
		// TODO Auto-generated method stub
		if (!thread2.isInterrupted()) {
		
			System.out.println(thread2.getName()+ thread2.isInterrupted());
			thread2.interrupt();
			System.out.println(thread2.getName()+ thread2.isInterrupted());
			int count =Thread.activeCount();
			System.out.println("当前进程："+count);
			Thread[] threads = new Thread[count];
			Thread.enumerate(threads);
			for(Thread th:threads) {
				if (th!=null) {
					System.out.println(th.getName());
				}
				
			}
		}
	}
/*	public void stop() {
		new Thread() {
			public void run() {
				try {
					int count =Thread.activeCount();
					System.out.println("当前进程："+count);
					System.out.println("当前进程："+currentThread().getName());
					Thread[] threads = new Thread[count];
					Thread.enumerate(threads);
					for(Thread th:threads) {
						if (th!=null) {
							System.out.println(th.getName());
						}
						
					}
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}.start();
		
	}*/
	//继续//清空记录
	public void con() {
		//System.out.println(thread2.interrupted());
		//new MyAction().start(startt);
		this.notifyAll();
	}


	

}
