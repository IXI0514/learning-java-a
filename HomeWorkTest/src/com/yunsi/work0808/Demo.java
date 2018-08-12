package com.yunsi.work0808;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Demo {
	
	public void start (String source,String target) {//��������д���
		try {
			File fs = new File(source);
			//System.out.println(fs.getPath());//check
			if (!fs.exists()) {
				throw new FileNotFoundException();
			}
			File ft = new File(target);
			if (ft.isFile()) {
				throw new IllegalArgumentException();
			}
			//System.out.println(ft.exists());//check
			if(!ft.exists()) {//����Ŀ��Ŀ¼������
				ft.mkdir();
			}
			this.copy(fs, ft);
		
		}catch (NullPointerException e){
			System.out.println("ԴĿ¼��Ŀ��Ŀ¼������Ϊ�գ���");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("ԴĿ¼�����ڣ���");
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			System.out.println("Ŀ��Ŀ¼Ϊ�ļ�����������ִ�в���");
			e.printStackTrace();
		}
		
	}//end start
	
	public void copy(File source,File target) {//ʵ�ʸ��Ƶķ���
		if(source.isFile()) {
			InputStream streamin=null;
			OutputStream streamout=null;
			try {
				 streamin = new FileInputStream(source);
				target = new File(target.getPath()+File.separator+source.getName());//�����µ�Ŀ���ļ���
				 streamout = new FileOutputStream(target);
				byte[] rebyte = new byte[5];
				int readlen = -1;
				while ((readlen=streamin.read(rebyte))!=-1) {
					streamout.write(rebyte,0,readlen);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(streamin!=null) {
					try {
						streamin.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(streamout!=null) {
					try {
						streamout.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(source.isDirectory()) {
			target = new File(target.getPath()+File.separator+source.getName());//�����µ�Ŀ���ļ���
			//System.out.println(target.getPath());
			//System.out.println(source.getPath());//check
			target.mkdirs();
			find(source,target);//����find�£�Ѱ��source���¼�Ŀ¼
		}
		
	}//end copy
	
	public void find (File f,File t) {
		File[]	flist =f.listFiles();
		for (int i=0;i<flist.length;i++) {
			this.copy(flist[i], t);//��copy�����ļ������ļ�or�ļ���
		}
	}//end find 
	
	public void delete (String source) {
		File fs = new File(source);
		if (fs.isFile()) {//������ļ�ֱ��ɾ��
			fs.delete();
		}else {
			File[] file = fs.listFiles();//�ҳ�Ŀ¼�µ��ļ����ƣ���������ɾ��(�ݹ�)
			if(file!=null&&file.length>0) {
				for (int i=0;i<file.length;i++) {
					boolean b = file[i].delete();
					if (!b) {
						this.delete(file[i].getPath());
						//System.out.println("for:"+file[i]); //check
					}				
				}
			}
			fs.delete();//����ǿ��ļ��о�ɾ��
		}
	}//end delete
	
	
	public void cut (String source,String target) {//����=����copy+ɾ��delete
		this.start(source, target);
		this.delete(source);
		
	}
	
	
}
