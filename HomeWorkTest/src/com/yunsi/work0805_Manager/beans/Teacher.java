package com.yunsi.work0805_Manager.beans;

public class Teacher extends People{
  private String tid;//���Ϊ��ʦ
  private String course;
  public  Teacher (){}
  public  Teacher (String pid,String name,int age,String home,String course){
    super( pid, name, age, home);
    setTid("��ʦ");
    setCourse(course);
  }
  public void show(){
    System.out.println(" Tid: "+this.tid+" Major: "+this.course);

  }

  //get set
  public void setTid(String tid){
    this.tid=tid;
  }
  public String getTid(){
    return tid;
  }
  public void setCourse(String course){
    this.course=course;
  }
  public String getCourse(){
    return course;
  }

}
