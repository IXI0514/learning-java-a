package com.yunsi.work0810_2.frame;

import java.util.Scanner;

import com.yunsi.work0810.service.IHRManagement;
import com.yunsi.work0810.service.impl.HRManagementImpl;
/**
 * 
 * 	管理系统的界面设计
 * @author ShenBL
 *
 */
public class FrameUI {
	IHRManagement ihr = new HRManagementImpl();
	IHRManagement ihrm = new HRManagementImpl();
	Scanner sc = new Scanner(System.in);
	
	public void start() {//开始
		ihrm.reset();//调用方法，读取文件中的对象
		outer:while(true) {
			System.out.println("========选择命令=========");
			System.out.println("=     1.添加人员信息              =");
			System.out.println("=     2.删除人员信息              =");
			System.out.println("=     3.更新人员信息              =");
			System.out.println("=     4.显示人员信息              =");
			System.out.println("=     5.显示所有信息              =");
			System.out.println("=      6.  退    出                    =");
			System.out.println("==========End==========");
			
			int start = sc.nextInt();
			switch (start) {//根据输入改变界面
				case 1:	this.addFrame();break;
				case 2:	this.deleteFrame();break;
				case 3:	this.updateFrame();break;
				case 4:	this.showInfoFrame();break;
				case 5:	this.showAllFrame();break;
				case 6:	ihr.save();break outer;
				default:System.out.println("\n   Error01：输入的命令有误！！\n");continue outer;
			}	
		}
	}

	public void addFrame() {
		
		System.out.println("========添加人员：=========");
		System.out.print("=     编号：   ");
		String pid =sc.next();
		System.out.print("=     姓名：   ");
		String name =sc.next();
		System.out.print("=     年龄：   ");
		int age =sc.nextInt();
		System.out.print("=     性别：   ");
		String sex =sc.next();
		System.out.print("=     职业(t:教师，s:学生)：   ");		
		String job =sc.next();
		System.out.print("=     备注：   ");
		String mark=sc.next();
		System.out.println("==========End============");
		int b =ihr.find(pid);//确定人员编号是否存在 存入时id不能重复
		if(b==-1) {
			ihr.add( pid,name,age,sex,job,mark);
		}else {
			System.out.println("\n   Error02：输入的编号有误，已存在！！\n");
			this.start();
		}
	}

	public void deleteFrame() {
		System.out.println("========删除人员：=========");
		System.out.print("=     输入用户的编号：   ");
		String pid =sc.next();
		int b =ihr.find(pid);//确定人员编号是否存在
		if(b!=-1) {
			ihr.delete(pid);
		}else {
			System.out.println("\n   Error02：输入的编号有误，不存在！！\n");
			this.start();
		}
	}

	public void updateFrame() {
		System.out.println("========更新人员：=========");
		System.out.print("=     输入用户的编号：   ");
		String pid =sc.next();
		int b =ihr.find(pid);//确定人员编号是否存在
		if(b!=-1) {
			System.out.println("========更新人员：=========");
			System.out.print("=     姓名：   ");
			String name =sc.next();
			System.out.print("=     年龄：   ");
			int age =sc.nextInt();
			System.out.print("=     性别：   ");
			String sex =sc.next();
			System.out.print("=     职业(t:教师，s:学生)：   ");		
			String job =sc.next();
			System.out.print("=     备注：   ");
			String mark=sc.next();
			System.out.println("==========End============");
			ihr.update(pid,name,age,sex,job,mark);
		}else {
			System.out.println("\n   Error02：输入的编号有误，不存在！！\n");
			this.start();
		}
	}

	public void showInfoFrame() {
		System.out.println("========显示人员信息：=========");
		System.out.print("=     输入用户的编号：   ");
		String pid =sc.next();
		int b =ihr.find(pid);//确定人员编号是否存在
		if(b!=-1){
			ihr.showInfo(pid);
		}else {
			System.out.println("\n   Error02：输入的编号有误，不存在！！\n");
			this.start();
		}
		
	}

	public void showAllFrame() {
		System.out.println("========显示所有人员：=========");
		ihr.showAll();
	}



}
