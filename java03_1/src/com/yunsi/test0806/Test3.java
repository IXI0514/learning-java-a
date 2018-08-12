package com.yunsi.test0806;

public class Test3 {
	 public static void main(String[] args) {
		 System.out.println("----Start----");
		 try {
			 System.exit(0);
			 System.out.println("------1------");
			 String str = null;
			 str =str.trim();
			 System.out.println("------2------");
			// return;
			 
		}catch (Exception e) {
			
			System.out.println("----Error----");
		} 
		 finally {
			 System.out.println("----Finally----");
			// TODO: handle finally clause
		}
		 System.out.println("----End----");
	}
}
