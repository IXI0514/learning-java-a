package com.yunsi.work0808;

import java.io.File;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		
		System.out.println("选择操作方式：\n---1.复制\n---2.剪切\n---3.删除");//选择复制还是粘贴
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		Demo d = new Demo();
		System.out.print(" 输入源文件目录：");//源文件目录，如果不存在会报错
		String source=sc.next();
		String target = null;
		if(x!=3) {
			System.out.print(" 输入目标目录：");//目标文件目录，如果没有文件夹会创建
			target=sc.next();
		}
		
		
		switch(x) {
			case 1 : d.start(source,target);
					 System.out.println("---复制完成---");
					 break;
			case 2 : d.cut(source,target);
					 System.out.println("---剪切完成---");
					 break;
			case 3 : d.delete(source);
					 System.out.println("---删除完成---");
					break;
			default :System.out.println("选择的信息有误！！！");
		}		
	}
		
		
		
}

