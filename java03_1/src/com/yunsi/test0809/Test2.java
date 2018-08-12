package com.yunsi.test0809;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * ObjectOutputStream 
 * ObjectInputStream
 *  	异常：
 * 			java.io.Serializable
 * 			java.lang.ClassNotFoundException
 * 			java.io.NotSerializableException
 * @author ShenBL
 *
 */


class Student implements Serializable{//学生类 对象
	public String name ;
	public int age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Student (String name ,int age) {
		setName(name);
		setAge(age);
	}
}
public class Test2 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		case0();
		//case1();
		Student s1 = new Student("云思",3);
		Student s2 = (Student)case2(s1);
		System.out.println("s1="+s1+" name="+s1.name+","+s1.age);
		System.out.println("s2="+s2+" name="+s2.name+","+s2.age);
	
	}
	public static void case0() throws IOException {
		OutputStream out = new FileOutputStream("e:/in.txt");
		ObjectOutputStream objo = new ObjectOutputStream(out);
//		String str = "aaa";
//    	String str2 = "aaa";
//  		String str3 = new String("aaa");
    	String str = new String("我爱中国");
		System.out.println("writeObject  之前str="+str+" 内存地址："+Integer.toHexString(str.hashCode()));
		objo.writeObject(str);
		objo.close();
	}
	public static void  case1() throws IOException, ClassNotFoundException {
		InputStream in = new FileInputStream("e:/in.txt");
		ObjectInputStream objs = new ObjectInputStream(in );
		String str = (String)objs.readObject();
		System.out.println(str+"  "+Integer.toHexString(str.hashCode()));
		objs.close();
	}
	public static Object case2 (Serializable obj) {
		ObjectInputStream obji=null;
		ObjectOutputStream objo =null;
		Object result = null;
		
		try {
			ByteArrayOutputStream baos =new ByteArrayOutputStream();
			objo  = new ObjectOutputStream(baos);
			objo.writeObject(obj);
			byte[] objDatas = baos.toByteArray();
			
			ByteArrayInputStream bais = new ByteArrayInputStream(objDatas);
			obji = new ObjectInputStream(bais);
			result = obji.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return  result;
	}
	
	

}
