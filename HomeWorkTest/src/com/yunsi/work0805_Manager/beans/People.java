package com.yunsi.work0805_Manager.beans;

public abstract class People{
  private String pid;
  private String name;
  private int age;
  private String home;

  public People(){}
  public  People(String pid,String name,int age,String home){
      setPid(pid);
      setName(name);
      setAge(age);
      setHome(home);
  }

  public abstract void show();
  //get set ·½·¨
  public void setPid(String pid){
    this.pid=pid;
  }
  public void setName(String name){
    this.name=name;
  }
  public void setAge(int age){
    this.age=age;
  }
  public void setHome(String home){
    this.home= home;
  }
  public String getPid(){
    return pid;
  }
  public String getName(){
    return name;
  }
  public int getAge(){
    return age;
  }
  public String getHome(){
    return home;
  }
}
