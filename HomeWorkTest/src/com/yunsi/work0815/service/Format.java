package com.yunsi.work0815.service;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.yunsi.work0815.bean.Student;

public class Format {
	public static final String PATH ="e:/data.txt"; 
	 
	public void formatData() {
		 Comparator<Student> comp = new Comparator<Student>() {//重写排序方法，主序：身高降序，次序：年龄升序
			@Override
			public int compare(Student o1, Student o2) {
				if(o1!=null&&o2!=null) {
					if (o1.getHeight()<o2.getHeight()) {
						return 1;
					}else if (o1.getHeight()>o2.getHeight()) {
						return -1;
					}else {
						return o1.getAge()>o2.getAge()?1:(o1.getAge()<o2.getAge()?-1:1);
					}
				}
				return 0;
			}
		};
		 
		 
		 Set<Student> list = new TreeSet<Student>(comp);
		 BufferedReader buffer = null;
		 PrintWriter writer = null;
			try {
				buffer = new BufferedReader(new FileReader(PATH));
				String line = null;
				while((line=buffer.readLine())!=null) {//读取并添加到set集合中，过程中会被排序
					Student student = this.test(line);
					list.add(student);
				}
				
				Iterator<Student> iterator = list.iterator();
				while(iterator.hasNext()) {
					System.out.println(iterator.next());
				}
				
				writer = new PrintWriter(new FileWriter(PATH));
				for(Student  s : list) {
					String str2 =s.printInfo();
					writer.println();
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (buffer!= null) {
				try {
					buffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (writer!= null) {
				writer.close();
			}
		}
	 }
	
	private   Student test(String line) {//处理行信息，返回student
		if(line!=null) {
			String[] str1 = line.split("@");
			if(str1!=null&&str1.length==2) {
				String[] datas = str1[1].split("#");
				Student student = new Student(datas[0], datas[1], Integer.parseInt(datas[2]),Integer.parseInt(datas[3]));
				//System.out.println("get："+student.getSid());//check
				return student;
			}
			
		}
		return null;
		
	}
	 
}
