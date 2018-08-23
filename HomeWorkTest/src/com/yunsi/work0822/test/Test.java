package com.yunsi.work0822.test;

import com.yunsi.work0822.FZexception.FZException;
import com.yunsi.work0822.findandzip.FindoZip;
import com.yunsi.work0822.thread.FindThread;
import com.yunsi.work0822.thread.ZipThread;

public class Test {
	public static void main(String[] args) {
		FindoZip findoZip = new FindoZip();
		//提供共享平台
		
		
		
		/*Thread[] findThreads = new Thread[1];//注：不能用多个线程读取，会重复查找
		FindThread findThread = new FindThread(findoZip);
		for (int i=0;i<findThreads.length;i++) {
			findThreads[i]=new Thread(findThread,"Find_"+(i+1));
			findThreads[i].start();
		}
		
		Thread[] zipThreads = new Thread[4];
		ZipThread zipThread = new ZipThread(findoZip);
		for (int i=0;i<zipThreads.length;i++){
			zipThreads[i] = new Thread(zipThread,"Zip_"+(i+1));
			zipThreads[i].start();
		}*/
		
		
		
/*		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		findoZip.getState();
		int num =Thread.activeCount();
		System.out.println(num);
		
		Thread[] threads = new Thread[num];
		Thread.enumerate(threads);
		for (Thread thread : threads) {
			if (thread!=null) {
				System.out.println(thread.getName());
			}
		}*/
	}
}
