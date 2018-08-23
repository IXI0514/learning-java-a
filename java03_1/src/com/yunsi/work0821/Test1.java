package com.yunsi.work0821;
/**
 * 
 * µÈ´ıÏß³ÌËÀÍö join()
 * @author ShenBL
 *
 */
public class Test1 {
	public static void main(String[] args) {
		int[] sums = new int[2]; 
		Thread thread = new Thread() {
			public void run() {
				for(int i=0;i<51;i++) {
					sums[0]+=i;
				}
				
			}	
		};
		thread.start();
		for (int i = 51; i < 101; i++) {
			sums[1]+=i;
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sums[0]+" "+sums[1]);
		int sum = sums[1]+sums[0];
		System.out.println("sum ="+sum);
	}
}
