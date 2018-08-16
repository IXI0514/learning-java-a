package com.yunsi.work0815;


import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Demo {
	


	public static void main(String[] args) {
		Comparator<Student> comp = new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				
				return 0;
			}
		};
			
			
		Set<Student> student = new TreeSet<Student>(comp);
		
		Student s1 = new Student("p001","shen");
		Student s2 = new Student("p002","bang");
		Student s3 = new Student("p002","shen");
		Student s4 = new Student("p003","bang");
		
		student.add(s2);
		student.add(s4);
		student.add(s1);
		student.add(s3);
		System.out.println(student);
	}
	
	public void test1() {
		Set<Student> student = new TreeSet<>();
		
		
		Student s1 = new Student("p001","shen");
		Student s2 = new Student("p002","bang");
		Student s3 = new Student("p002","shen");
		Student s4 = new Student("p003","bang");
		
		//存入顺序重复问题
//		System.out.println("s1.hashCode:"+s1);
//		System.out.println("s2.hashCode:"+s2);
//		System.out.println("s1.hashCode:"+s1.hashCode());
//		System.out.println("s1.hashCode:"+s2.hashCode());
		
		
		student.add(s2);
		student.add(s4);
		student.add(s1);
		student.add(s3);
		System.out.println(student);
	}
}
