package com.yunsi.work0809;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

import javax.management.RuntimeErrorException;

public class Demo {
	public static int DEFAULT_LEN=10;//初始数组容量
	
	
	public File format(String s) {//方法重写，参数动态
		File f =new File (s);
		return this.format(f);
	}
	
	public File format(File f) {// 处理文档中的空字符串  格式化文档  返回File （输出文件目录）
		Reader r =null;
		Writer w =null;
		File fout = null;
		try {	
			if (f.isDirectory()) {//考虑目录是不是文件夹，如果是则报错
				throw new RuntimeErrorException(null);
			}
			r = new FileReader(f);
			String outdir = new String(f.getParentFile()+File.separator+"temp.txt");
			fout = new File(outdir);
			//System.out.println(fout.getPath());//check输出目录
			w = new FileWriter(fout);
			BufferedReader br = new BufferedReader(r);//过滤换行
			String line = null;
			while ((line = br.readLine())!=null) {
				String str = line.trim();
				for(int i=0;i<str.length();i++) {
					char ch = str.charAt(i);
					if(ch==' '||ch=='\t') {//过滤空格和制表符
						continue;
					}
					//System.out.print(ch);//check
					w.write(ch);//写入到temp.text中
				}
			}
		}catch (RuntimeErrorException e) {
			System.out.println("处理对象不能是文件夹！！");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("处理对象文件不存在或为空！！");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {//关闭IO
			if(r!=null) {
				try {
					r.close();
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fout;
	}//end format

	// 字频统计
	public void statistics(File f) {
		char[] ch = new char[DEFAULT_LEN];//用于存字符
		int [] num =new int[DEFAULT_LEN];//用于存频率
		int idx =0;
	
		Reader r = null;
		Writer w = null;
		File fout = null;
		try {
			r = new FileReader(f);
			String outdir = new String(f.getParentFile()+File.separator+"out.txt");//输出目录
			fout = new File(outdir);
			w = new FileWriter(fout);
			int read=-1;
			while((read=r.read())!=-1) {
				char chr=(char)read;
				//System.out.print("\n-"+chr+ idx+"  " +(idx>=DEFAULT_LEN-1));//check
				if(idx>=DEFAULT_LEN) {//数组扩容 
					DEFAULT_LEN*=2;//原来数组的二倍
					char[] temp1 = Arrays.copyOf(ch, DEFAULT_LEN);
					int[] temp2 = Arrays.copyOf(num, DEFAULT_LEN);
					ch=temp1;
					num=temp2;
				}
				
				boolean b=false;
				for(int i=0;i<=idx;i++) {//查找数组中是否存在当前字符
					if((int)ch[i]==read) {
						num[i]++;
						b=true;
					}
				}
				if(!b) {//不存在就在ch数组中创建
					ch[idx]=chr;
					num[idx]=1;
					idx++;
				}
			}//end while
			//System.out.println("xxxxx");//check
			//System.out.println(DEFAULT_LEN);//check
			
			for(int i=0;i<DEFAULT_LEN;i++) {//将结果输出到out.txt中
				if(num[i]!=0) {
					w.write(ch[i]);
					w.write(":");
					String a = String.valueOf(num[i]);
					w.write(a);
					w.write("\t");
				}	
			}
		} catch (FileNotFoundException e) {
			System.out.println("处理对象文件不存在或为空！！");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (r!=null) {
				try {
					r.close();
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		int index =this.forMax(num);
		System.out.println("\n ——>出现次数最多的字符是："+ch[index]+",出现了："+num[index]+"次");
	}//end  statistics
	
	public int forMax(int[] a) {//找出现最多的数的下标
		int max=a[0];
		int result=0;
		for (int i=1;i<a.length;i++) {
			if(max<a[i]) {
				max=a[i];
				result=i;
			}
		}
		return result;
	}
}
