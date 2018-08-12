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
	public static int DEFAULT_LEN=10;//��ʼ��������
	
	
	public File format(String s) {//������д��������̬
		File f =new File (s);
		return this.format(f);
	}
	
	public File format(File f) {// �����ĵ��еĿ��ַ���  ��ʽ���ĵ�  ����File ������ļ�Ŀ¼��
		Reader r =null;
		Writer w =null;
		File fout = null;
		try {	
			if (f.isDirectory()) {//����Ŀ¼�ǲ����ļ��У�������򱨴�
				throw new RuntimeErrorException(null);
			}
			r = new FileReader(f);
			String outdir = new String(f.getParentFile()+File.separator+"temp.txt");
			fout = new File(outdir);
			//System.out.println(fout.getPath());//check���Ŀ¼
			w = new FileWriter(fout);
			BufferedReader br = new BufferedReader(r);//���˻���
			String line = null;
			while ((line = br.readLine())!=null) {
				String str = line.trim();
				for(int i=0;i<str.length();i++) {
					char ch = str.charAt(i);
					if(ch==' '||ch=='\t') {//���˿ո���Ʊ��
						continue;
					}
					//System.out.print(ch);//check
					w.write(ch);//д�뵽temp.text��
				}
			}
		}catch (RuntimeErrorException e) {
			System.out.println("������������ļ��У���");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("��������ļ������ڻ�Ϊ�գ���");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {//�ر�IO
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

	// ��Ƶͳ��
	public void statistics(File f) {
		char[] ch = new char[DEFAULT_LEN];//���ڴ��ַ�
		int [] num =new int[DEFAULT_LEN];//���ڴ�Ƶ��
		int idx =0;
	
		Reader r = null;
		Writer w = null;
		File fout = null;
		try {
			r = new FileReader(f);
			String outdir = new String(f.getParentFile()+File.separator+"out.txt");//���Ŀ¼
			fout = new File(outdir);
			w = new FileWriter(fout);
			int read=-1;
			while((read=r.read())!=-1) {
				char chr=(char)read;
				//System.out.print("\n-"+chr+ idx+"  " +(idx>=DEFAULT_LEN-1));//check
				if(idx>=DEFAULT_LEN) {//�������� 
					DEFAULT_LEN*=2;//ԭ������Ķ���
					char[] temp1 = Arrays.copyOf(ch, DEFAULT_LEN);
					int[] temp2 = Arrays.copyOf(num, DEFAULT_LEN);
					ch=temp1;
					num=temp2;
				}
				
				boolean b=false;
				for(int i=0;i<=idx;i++) {//�����������Ƿ���ڵ�ǰ�ַ�
					if((int)ch[i]==read) {
						num[i]++;
						b=true;
					}
				}
				if(!b) {//�����ھ���ch�����д���
					ch[idx]=chr;
					num[idx]=1;
					idx++;
				}
			}//end while
			//System.out.println("xxxxx");//check
			//System.out.println(DEFAULT_LEN);//check
			
			for(int i=0;i<DEFAULT_LEN;i++) {//����������out.txt��
				if(num[i]!=0) {
					w.write(ch[i]);
					w.write(":");
					String a = String.valueOf(num[i]);
					w.write(a);
					w.write("\t");
				}	
			}
		} catch (FileNotFoundException e) {
			System.out.println("��������ļ������ڻ�Ϊ�գ���");
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
		System.out.println("\n ����>���ִ��������ַ��ǣ�"+ch[index]+",�����ˣ�"+num[index]+"��");
	}//end  statistics
	
	public int forMax(int[] a) {//�ҳ������������±�
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
