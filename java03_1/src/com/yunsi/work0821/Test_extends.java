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
					System.out.println(Thread.currentThread().getName()+"--����Ʊ��" + num--);
				}
			}
		}
		
		
		public static void main(String[] args) {
			new Test_extends().start();
			new Test_extends().start();
			new Test_extends().start();
		
			//ÿ���̶߳���������������Դ��ÿ���̶߳�������10��Ʊ���ܹ�������30�š��������Ʊ�����������ˡ�
		}
	 
	}

	
	

