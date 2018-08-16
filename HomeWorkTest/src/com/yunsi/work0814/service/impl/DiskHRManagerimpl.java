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
 * 	基于磁盘实现HRM
 * @author ShenBL
 *
 */
public class DiskHRManagerimpl implements HRManagerIF{
	final static String PATH = "e:/temp/temp.txt";//文件位置
	
	//初始化文件	
	public DiskHRManagerimpl() {
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
	
	//添加人员信息
	@Override
	public void add(People p) throws HRMException {
		if(find(p.getPid())!=null){
				throw new HRMException("输入的编号已存在！！");	
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
	//删除人员信息
	@Override
	public void delete(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("输入的编号不存在，无法删除！！");	
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
		System.out.println("删除完成！！");
	}
	//更新人员信息
	@Override
	public void update(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("输入的编号不存在，无法更新！！");	
		}
		MainUI ui = new MainUI();//调用更新输入界面
		People people = ui.common();//更新的信息
		String job0 = find(pid).getJob();
		String job = people.getJob();
	
		if(find(people.getPid())!=null){//检查新信息中的id是否已存在
			throw new HRMException("输入的编号已被占用！！");	
		}
		if(!job0.equals(job)){//如果职业不同不能更换
			throw new HRMException("职业不同，不支持更新！！");	
		}
		People[] temp =this.Demo();
		PrintWriter w = null;
		try {
			w = new PrintWriter(new FileWriter(PATH,false));
			for(int i=0;i<temp.length-1;i++) {
				boolean b = temp[i].getPid().equals(pid);
				if (b) {
					w.println(people.getInfo());//如果是要更新的位置就写入新的信息
				}else {
					w.println(temp[i].getInfo());//其他的就直接复制
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(w!=null) {
				w.close();
			}
		}
		System.out.println("更新完毕！！");
		
	}
	//显示人员信息
	@Override
	public void show(String pid) throws HRMException {
		if(find(pid)==null){
			throw new HRMException("输入的编号不存在，无法显示信息！！");	
		}
		People people = find(pid);
		String info = people.showInfo();
		System.out.println("\n"+info+"\n");
		
	}
	//显示所有
	@Override
	public void showAll() throws HRMException {
		People[] peoples = this.Demo();
		if(peoples.length==1) {
			throw new HRMException("当前无在册人员！！");
		}
		for(int i=0;i<peoples.length-1;i++) {
			System.out.println("\n"+peoples[i].showInfo()+"\n");
		}
	}
	//根据id寻找People
	private People find(String pid)  {
		People[] peoples= this.Demo();
		for (int i =0;i<peoples.length-1;i++) {
			if (peoples[i]!=null&&((peoples[i].getPid()).equals(pid))) {
				return peoples[i];
			}
		}
		return null;	
	}
	
	//获取整个文档中的信息 存在数组中 方便匹配查找
	private People[] Demo() {
		People[] p = new People[1];
		int index = 0;
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new FileReader(PATH));
			String line = null;//每行的信息:职业@详细信息
			while((line=buffer.readLine())!=null) {
				String[] data = line.split("@");
				String  str0 = data[0];//职业
				String  str1 = data[1];//详细信息
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
				p = Arrays.copyOf(p, p.length+1);//数组扩容，再存入之后扩容，有效长度会-1
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
