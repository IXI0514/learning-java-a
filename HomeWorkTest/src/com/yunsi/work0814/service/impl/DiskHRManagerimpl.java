package com.yunsi.work0814.service.impl;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


import com.yunsi.work0814.beans.People;
import com.yunsi.work0814.beans.sub.Athlete;
import com.yunsi.work0814.beans.sub.Doctor;
import com.yunsi.work0814.beans.sub.Teacher;
import com.yunsi.work0814.exception.HRMException;
import com.yunsi.work0814.frame.MainUI;
import com.yunsi.work0814.service.HRManagerIF;
/**
 * 
 * 	���ڴ���ʵ��HRM
 * @author ShenBL
 *
 */
public class DiskHRManagerimpl implements HRManagerIF{
	final static String PATH = "e:/temp/temp.txt";//�ļ�λ��
	
	//��ʼ���ļ�
	private static void reset() {
		File file = new File(PATH);
		if(!file.exists()) {
			File str = file.getParentFile();
			str.mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	


	//�����Ա��Ϣ
	@Override
	public void add(People p) throws HRMException {
		if(find(p.getPid())!=null){
				throw new HRMException("����ı���Ѵ��ڣ���");	
		}
		PrintWriter w = null;
		try {
			w = new PrintWriter(new FileWriter(PATH,true));
			w.println(p.getInfo());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(w!=null) {
				w.close();
			}
		}
	}
	//ɾ����Ա��Ϣ
	@Override
	public void delete(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("����ı�Ų����ڣ��޷�ɾ������");	
		}
		People[] temp =this.Demo();
		PrintWriter w = null;
		try {
			w = new PrintWriter(new FileWriter(PATH,false));
			for(int i=0;i<temp.length-1;i++) {
				boolean b = temp[i].getPid().equals(pid);
				if (!b) {
					w.println(temp[i].getInfo());
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(w!=null) {
				w.close();
			}
		}
		System.out.println("ɾ����ɣ���");
	}
	//������Ա��Ϣ
	@Override
	public void update(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("����ı�Ų����ڣ��޷����£���");	
		}
		MainUI ui = new MainUI();//���ø����������
		People people = ui.common();//���µ���Ϣ
		String job0 = find(pid).getJob();
		String job = people.getJob();
	
		if(find(people.getPid())!=null){//�������Ϣ�е�id�Ƿ��Ѵ���
			throw new HRMException("����ı���ѱ�ռ�ã���");	
		}
		if(!job0.equals(job)){//���ְҵ��ͬ���ܸ���
			throw new HRMException("ְҵ��ͬ����֧�ָ��£���");	
		}
		People[] temp =this.Demo();
		PrintWriter w = null;
		try {
			w = new PrintWriter(new FileWriter(PATH,false));
			for(int i=0;i<temp.length-1;i++) {
				boolean b = temp[i].getPid().equals(pid);
				if (b) {
					w.println(people.getInfo());//�����Ҫ���µ�λ�þ�д���µ���Ϣ
				}else {
					w.println(temp[i].getInfo());//�����ľ�ֱ�Ӹ���
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(w!=null) {
				w.close();
			}
		}
		System.out.println("������ϣ���");
		
	}
	//��ʾ��Ա��Ϣ
	@Override
	public void show(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("����ı�Ų����ڣ��޷���ʾ��Ϣ����");	
		}
		People people = find(pid);
		String info = people.showInfo();
		System.out.println("\n"+info+"\n");
		
	}
	//��ʾ����
	@Override
	public void showAll() throws HRMException {
		People[] peoples = this.Demo();
		if(peoples.length==1) {
			throw new HRMException("��ǰ���ڲ���Ա����");
		}
		for(int i=0;i<peoples.length-1;i++) {
			System.out.println("\n"+peoples[i].showInfo()+"\n");
		}
	}
	//����idѰ��People
	private People find(String pid)  {
		People[] peoples= this.Demo();
		for (int i =0;i<peoples.length-1;i++) {
			if (peoples[i]!=null&&((peoples[i].getPid()).equals(pid))) {
				return peoples[i];
			}
		}
		return null;	
	}
	
	//��ȡ�����ĵ��е���Ϣ ���������� ����ƥ�����
	private People[] Demo() {
		People[] p = new People[1];
		int index = 0;
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new FileReader(PATH));
			String line = null;//ÿ�е���Ϣ:ְҵ@��ϸ��Ϣ
			while((line=buffer.readLine())!=null) {
				String[] data = line.split("@");
				String  str0 = data[0];//ְҵ
				String  str1 = data[1];//��ϸ��Ϣ
				String[] datas = str1.split("#");
				People temp = null;
				if (str0.equalsIgnoreCase("Athlete")) {
					 temp = new Athlete(datas[0], datas[1], datas[2],Integer.parseInt(datas[3]), datas[4], datas[5]);
				} else if (str0.equalsIgnoreCase("Teacher")){
					temp = new Teacher(datas[0], datas[1], datas[2],Integer.parseInt(datas[3]), datas[4], datas[5],datas[6]);
				} else if (str0.equalsIgnoreCase("Doctor")){
					temp = new Doctor(datas[0], datas[1], datas[2],Integer.parseInt(datas[3]), datas[4], datas[5],datas[6]);
				}
				p[index] = temp;
				index++;
				p = Arrays.copyOf(p, p.length+1);//�������ݣ��ٴ���֮�����ݣ���Ч���Ȼ�-1
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (buffer!=null) {
				try {
					buffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}
	

}
