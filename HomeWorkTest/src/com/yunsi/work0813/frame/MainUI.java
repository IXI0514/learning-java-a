package com.yunsi.work0813.frame;

import java.util.Scanner;

import com.yunsi.work0813.beans.People;
import com.yunsi.work0813.beans.sub.Athlete;
import com.yunsi.work0813.beans.sub.Doctor;
import com.yunsi.work0813.beans.sub.Teacher;
import com.yunsi.work0813.exception.HRMException;
import com.yunsi.work0813.service.HRManagerIF;
import com.yunsi.work0813.service.impl.HRManagerimpl;


public class MainUI {
	HRManagerIF ihr = new HRManagerimpl();
	Scanner sc = new Scanner(System.in);
	
	public void start() {//开始
		ihr.reset();//程序重启时读取数据
		outer:while(true) {
			System.out.println("\n========选择命令===========");
			System.out.println("=     1.添加人员信息      =");
			System.out.println("=     2.删除人员信息      =");
			System.out.println("=     3.更新人员信息      =");
			System.out.println("=     4.显示人员信息      =");
			System.out.println("=     5.显示所有信息      =");
			System.out.println("=      6. 退    出        =");
			System.out.println("==========End==============\n");
			int start = sc.nextInt();
			switch (start) {//根据输入改变界面
				case 1:	this.addFrame();break;
				case 2:	this.deleteFrame();break;
				case 3:	this.updateFrame();break;
				case 4:	this.showInfoFrame();break;
				case 5:	this.showAllFrame();break;
				case 6:	break outer;
				default:System.out.println("\n   Error01：输入的命令有误！！\n");continue outer;
			}	
		}
	}
	//add update 共有的界面
	public People common() {
		System.out.print("=     编号：   ");
		String pid =sc.next();
		System.out.print("=     姓名：   ");
		String name =sc.next();
		System.out.print("=     年龄：   ");
		int age =sc.nextInt();
		System.out.print("=     性别：   ");
		String sex =sc.next();
		System.out.println("=     职业(a：运动员/d:医生/t：教师)：   ");		
		String job =sc.next();
		People people = null;
		switch (job) {
		case "A":case "a":{
			System.out.print("运动项目：");
			String sport = sc.next();
			Athlete a = new Athlete(pid, name, sex, age, "运动员", sport);
			people = a;
			
		}break;
		case "D":case "d":{
			System.out.println("医院：");
			String hospital =  sc.next();
			System.out.println("科目：");
			String dtype = sc.next();
			Doctor d = new Doctor( pid,  name,  sex,  age,  "医生",  hospital, dtype) ;
			people = d;
		}break;
		case "T":case "t":{
			System.out.println("学校：");
			String school =  sc.next();
			System.out.println("科目：");
			String course = sc.next();
			Teacher t = new Teacher( pid,  name,  sex,  age,  "教师",  school, course) ;
			people = t;
		}break;
		case "" :default:System.out.println("职业要按要求输入！！");this.start() ;
		}
		return people;
	}
	
	public void addFrame()  {
		System.out.println("========添加人员：=========");
		People people =this.common();
		try {
			ihr.add(people);
		} catch (HRMException e) {
			System.out.println(e);
			this.start();
		}
		System.out.println("==========End============");
	}

	public void deleteFrame()  {
		System.out.println("========删除人员：=========");
		System.out.print("=     输入用户的编号：   ");
		String pid =sc.next();
		try {
			ihr.delete(pid);
		} catch (HRMException e) {
			System.out.println(e);
			this.start();
		}
	}

	public void updateFrame() {
		System.out.println("========更新人员：=========");
		System.out.print("=     输入用户的编号：   ");
		String pid =sc.next();
		try {
			ihr.update(pid);
		} catch (HRMException e) {
			System.out.println(e);
			this.start();
		}
		
	}

	public void showInfoFrame() {
		System.out.println("========显示人员信息：=========");
		System.out.print("=     输入用户的编号：   ");
		String pid =sc.next();
		try {
			ihr.show(pid);
		} catch (HRMException e) {
			System.out.println(e);
			this.start();
		}
	}

	public void showAllFrame() {
		System.out.println("========显示所有人员：=========");
		ihr.showAll();
	}



}