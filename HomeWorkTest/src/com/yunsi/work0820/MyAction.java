package com.yunsi.work0820;

import java.text.SimpleDateFormat;
import java.util.Date;




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
		thread1.start();
		System.out.println("��ǰʱ��Ľ��̣�"+thread1.getName());
		
	}
	
	
	
	//��ʼ��ʱ
	public void start(long start) {//��ȡʱ�䲢������fram��
		
		thread2 = new Thread() {
			public void run() {
				while (true&&!thread2.isInterrupted()) {
					long now = System.currentTimeMillis();
					SimpleDateFormat sim= new SimpleDateFormat("mm:ss");
					String str0 = sim.format(now-start);
					 tipStr = String.valueOf(str0);
					MyFrame.tlab.setText("��ʱ��" + tipStr);
					
				}
			}
		};
		
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
		
	//ֹͣ//ֹͣ��ʱ
	public void stop() {
		// TODO Auto-generated method stub
		if (!thread2.isInterrupted()) {
			System.out.println(thread2.getName()+ thread2.isInterrupted());
			thread2.interrupt();
			System.out.println(thread2.getName()+ thread2.isInterrupted());
	
		
		}
	}
	//����//��ռ�¼
	public void con() {
		System.out.println(thread2.isInterrupted());
		System.out.println(Thread.interrupted());
		
	}


	

}
