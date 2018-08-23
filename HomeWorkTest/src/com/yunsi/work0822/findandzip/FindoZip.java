package com.yunsi.work0822.findandzip;

import java.io.File;
import java.lang.Thread.State;
import java.util.ArrayList;

import com.yunsi.work0822.FZexception.FZException;
import com.yunsi.work0822.factory.Factory;
import com.yunsi.work0822.thread.FindThread;
import com.yunsi.work0822.thread.ZipThread;

public class FindoZip {
	
	/*public static File path;//������Ŀ¼
	public static File zipPath;//ѹ��Ŀ¼
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
		File path = new File(prop[0]);//������Ŀ¼
		File zipPath = new File(prop[1]);//ѹ��Ŀ¼
		int fthreadnum = Integer.parseInt(prop[2]) ;//�����߳�����
		int zthreadnum = Integer.parseInt(prop[3]) ;//ѹ���߳�����
		//���������ȷ
		if(!path.exists()) {
			throw new FZException("Error:Ŀ¼�����ڣ���");
		}
		if(zipPath.isFile()) {
			throw new FZException("Error:��Ҫ�ṩĿ¼����");
		}
		if(!zipPath.exists()) {//����ļ��в����ھʹ���
			zipPath.mkdirs();
		}
		Thread[] findThreads = new Thread[fthreadnum];//ע�������ö���̶߳�ȡ�����ظ�����
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
		System.out.println("���"+file.getPath()+"��ɣ���");
	}
	*/
	
	/*public synchronized void find(File file) {//�ж�file���� ������ļ��о͵ݹ��жϴ���������ļ��ͷŵ������еȴ�ѹ��
		boolean b= file.isDirectory();
		if(b) {
			File[] temps = file.listFiles();
			if(temps!=null) {//�����ļ�����Ϊ��
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


	
	public void getState() {//��ȡ�����߳�״̬
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
