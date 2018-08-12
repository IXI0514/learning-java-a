package com.yunsi.work0808;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Demo {
	
	public void start (String source,String target) {//对输入进行处理
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
			if(!ft.exists()) {//考虑目标目录不存在
				ft.mkdir();
			}
			this.copy(fs, ft);
		
		}catch (NullPointerException e){
			System.out.println("源目录和目标目录都不能为空！！");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("源目录不存在！！");
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			System.out.println("目标目录为文件！！！不能执行操作");
			e.printStackTrace();
		}
		
	}//end start
	
	public void copy(File source,File target) {//实际复制的方法
		if(source.isFile()) {
			InputStream streamin=null;
			OutputStream streamout=null;
			try {
				 streamin = new FileInputStream(source);
				target = new File(target.getPath()+File.separator+source.getName());//构建新的目标文件夹
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
			target = new File(target.getPath()+File.separator+source.getName());//构建新的目标文件夹
			//System.out.println(target.getPath());
			//System.out.println(source.getPath());//check
			target.mkdirs();
			find(source,target);//传到find下，寻找source的下级目录
		}
		
	}//end copy
	
	public void find (File f,File t) {
		File[]	flist =f.listFiles();
		for (int i=0;i<flist.length;i++) {
			this.copy(flist[i], t);//调copy复制文件夹下文件or文件夹
		}
	}//end find 
	
	public void delete (String source) {
		File fs = new File(source);
		if (fs.isFile()) {//如果是文件直接删除
			fs.delete();
		}else {
			File[] file = fs.listFiles();//找出目录下的文件名称，有则依次删除(递归)
			if(file!=null&&file.length>0) {
				for (int i=0;i<file.length;i++) {
					boolean b = file[i].delete();
					if (!b) {
						this.delete(file[i].getPath());
						//System.out.println("for:"+file[i]); //check
					}				
				}
			}
			fs.delete();//如果是空文件夹就删除
		}
	}//end delete
	
	
	public void cut (String source,String target) {//剪切=复制copy+删除delete
		this.start(source, target);
		this.delete(source);
		
	}
	
	
}
