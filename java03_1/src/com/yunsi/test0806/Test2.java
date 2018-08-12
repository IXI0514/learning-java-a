package com.yunsi.test0806;
/**
 * Try ...catch
 * @author ShenBL
 *
 */
public class Test2 {
	public static void main(String[] args) {
		System.out.println("-----Before try catch-----");
		try {
			System.out.println("----1----");
			int[] arr =null;
			//int ele = arr[0];
			System.out.println("----2----");
			arr = new int [8];
			//arr[10]=100;
			System.out.println("----3-----");
			int a=1;
			int b=0;
			int c=a/b;
			System.out.println("----4-----");
			
		} catch (NullPointerException e) {
			System.out.println("npe逻辑异常");
			
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("下标越界异常");
		}catch (Exception e) {
			System.out.println("异常-Error!!");
		}
		System.out.println("=======End=========");
		
	
	}
}
