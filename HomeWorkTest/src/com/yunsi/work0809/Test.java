package com.yunsi.work0809;

import java.io.File;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		System.out.println("����Ҫͳ�Ƶ��ļ���λ�ã�");
		Scanner sc = new Scanner(System.in);
		String  str = sc.next();
		Demo d =new Demo();
		//File f=new File("F:\\YunSi\\1#Ftp\\�׶�1_0809\\ԭʼ�ļ�.txt");
		File f=d.format(str);
		d.statistics(f);
	}// end main
}
