package com.yunsi.work0815;

public class Student implements Comparable<Student>{
	private String sid;
	private String name;
	
	@Override
	public int compareTo(Student o) {
		int b = this.getSid().compareTo(o.getSid());
		if (b == 0) {
			int bb = this.name.compareTo(o.name);
			return bb;
		}
		return -b;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", name=" + name + "]\n";
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		return true;
	}
	public Student(String sid, String name) {
		super();
		this.sid = sid;
		this.name = name;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
