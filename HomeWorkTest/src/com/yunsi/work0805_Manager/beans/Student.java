package com.yunsi.work0805_Manager.beans;

public class Student extends People{
  private String sid;//���Ϊѧ�� (��)
  private String major;


  public  Student(){}
  public  Student(String pid,String name,int age,String home,String major){
    super(pid,name,age,home);
    setMajor(major);
    setSid("ѧ��");
  }

  public void show(){
    System.out.println(" Sid: "+this.sid+" Major: "+this.major);

  }
  //set get����
  public void setSid(String sid){
    this.sid=sid;
  }
  public String getSid(){
    return sid;
  }
  public void setMajor(String major){
    this.major=major;
  }
  public String getMajor(){
    return major;
  }

}
