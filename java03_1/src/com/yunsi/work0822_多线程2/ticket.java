package com.yunsi.work0822_多线程2;

public class ticket {
	private int num;
	public ticket() {
		num = 10;
	}
	public ticket(int a) {
		num = a;
	}
	
	public static void main(String[] args) {
		ticket t = new ticket();
		ticket t2 = new ticket();
		
		new Thread() {
			public void run() {
				synchronized(t) {
					System.out.println(currentThread().getName()+"t");
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized(t2) {
						System.out.println(currentThread().getName()+"t2");

						
					}
				}
			}
			
		}.start();
		new Thread() {
				public void run() {
						synchronized(t2) {
							System.out.println(currentThread().getName()+"t2");

							synchronized(t) {
								System.out.println(currentThread().getName()+"t");

				
					}
				}
			}
			
		}.start();
		/*new Thread() {
			public void run() {
				for (int i = 0;i<6;i++) {
					t.buyTicket();
					t2.buyTicket();
				}			
			}
		}.start();*/
	}

	public void buyTicket() {
		// TODO Auto-generated method stub
		System.out.println("剩余票数："+num--);
		
	}


}
