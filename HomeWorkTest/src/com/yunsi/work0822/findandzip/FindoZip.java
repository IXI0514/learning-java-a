package com.yunsi.work0822.findandzip;

import java.io.File;
import java.lang.Thread.State;
import java.util.ArrayList;

import com.yunsi.work0822.FZexception.FZException;
import com.yunsi.work0822.factory.Factory;
import com.yunsi.work0822.thread.FindThread;
import com.yunsi.work0822.thread.ZipThread;

public class FindoZip {
	
	/*public static File path;//待处理目录
	public static File zipPath;//压缩目录
*/	
	ArrayList<File> list = new ArrayList<File>();
	
	public FindoZip()  {
		
		try {
			start();
		} catch (FZException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	
	}
	
	private void start() throws FZException {
		String[] prop = new Factory().getProp();		
		File path = new File(prop[0]);//待处理目录
		File zipPath = new File(prop[1]);//压缩目录
		int fthreadnum = Integer.parseInt(prop[2]) ;//查找线程数量
		int zthreadnum = Integer.parseInt(prop[3]) ;//压缩线程数量
		//检查配置正确
		if(!path.exists()) {
			throw new FZException("Error:目录不存在！！");
		}
		if(zipPath.isFile()) {
			throw new FZException("Error:需要提供目录！！");
		}
		if(!zipPath.exists()) {//如果文件夹不存在就创建
			zipPath.mkdirs();
		}
		Thread[] findThreads = new Thread[fthreadnum];//注：不能用多个线程读取，会重复查找
		FindThread findThread = new FindThread(path,list);
		for (int i=0;i<findThreads.length;i++) {
			findThreads[i]=new Thread(findThread,"Find_"+(i+1));
			findThreads[i].start();
		}
		
		Thread[] zipThreads = new Thread[zthreadnum];
		ZipThread zipThread = new ZipThread(zipPath,list);
		for (int i=0;i<zipThreads.length;i++){
			zipThreads[i] = new Thread(zipThread,"Zip_"+(i+1));
			zipThreads[i].start();
		}
	}

	/*public synchronized void put(File file) {
		list.add(file);
		this.notifyAll();
		System.out.println("添加"+file.getPath()+"完成！！");
	}
	*/
	
	/*public synchronized void find(File file) {//判断file类型 如果是文件夹就递归判断处理，如果是文件就放到集合中等待压缩
		boolean b= file.isDirectory();
		if(b) {
			File[] temps = file.listFiles();
			if(temps!=null) {//考虑文件夹内为空
				for (File temp : temps) {
					find(temp);
				}
			}
		}else {
			list.add(file);
			//System.out.println(list.size());
			this.notifyAll();
			
			
			int num = Thread.activeCount();
			Thread[] threads = new Thread[num];
			Thread.enumerate(threads);
			for (Thread thread : threads) {
				System.out.println(thread.getName());
			}			
			
			System.out.println("=====num========="+num);
		}
		
	}*/


	
	public void getState() {//获取所有线程状态
		int num =Thread.activeCount();
		System.out.println(num);
		Thread[] threads = new Thread[num];
		Thread.enumerate(threads);
		/*boolean b=false;
		for (Thread thread : threads) {
			if (thread.getName().contains("Find")) {
				b=true;
			}
		}*/
		for (Thread thread : threads) {
			if (thread!=null) {
				System.out.println(thread.getName()+"::"+thread.getState());
				System.out.println(thread.getState()==State.WAITING);
				if (thread.getState()==State.WAITING) {
					//thread.interrupt();
					thread.stop();
				}
			}
		}
		System.out.println(Thread.activeCount());
		
	}
	
}
