package com.yunsi.work0820_�򵥼�ʱGui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;




/**
 * 
 * ��ʱ�ķ���
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
					MyFrame.date.setText("��ǰʱ�䣺"+dateStr);//��ǰʱ��
	
				}
			}
		};
		thread1.setName("CurrDate_thread");
		thread1.start();
		System.out.println("��ǰʱ��Ľ��̣�"+thread1.getName());
		
	}
	
	
	
	//��ʼ��ʱ
	public void start(long start) {//��ȡʱ�䲢������fram��
		startt = start;
		thread2 = new Thread() {
			public void run() {
				while (!thread2.isInterrupted()) {
					long now = System.currentTimeMillis();
					SimpleDateFormat sim= new SimpleDateFormat("mm:ss");
					String str0 = sim.format(now-start);
					 tipStr = String.valueOf(str0);
					MyFrame.tlab.setText("��ʱ��" + tipStr);
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
						MyFrame.tlab.setText("��ʱ��" + tipStr);
					}else {
						System.out.println("�ж��С�����");
					}
				}*/
			}
		};
		thread2.setName("Time_thread");
		thread2.start();
		System.out.println("��ʱ���̣�"+thread2.getName());
	}	
	//�Ǵ� //���º��¼��ǰʱ����ӵ��б���
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
		
	//��ͣ//��ͣ��ʱ
	public void stop() {
		// TODO Auto-generated method stub
		if (!thread2.isInterrupted()) {
		
			System.out.println(thread2.getName()+ thread2.isInterrupted());
			thread2.interrupt();
			System.out.println(thread2.getName()+ thread2.isInterrupted());
			int count =Thread.activeCount();
			System.out.println("��ǰ���̣�"+count);
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
					System.out.println("��ǰ���̣�"+count);
					System.out.println("��ǰ���̣�"+currentThread().getName());
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
	//����//��ռ�¼
	public void con() {
		//System.out.println(thread2.interrupted());
		//new MyAction().start(startt);
		this.notifyAll();
	}


	

}
