package com.yunsi.work0805_Manager.service.impl;


import com.yunsi.work0805_Manager.beans.People;
import com.yunsi.work0805_Manager.beans.Student;
import com.yunsi.work0805_Manager.beans.Teacher;
import com.yunsi.work0805_Manager.service.IManager;

import java.util.Scanner;

public class ManagerImpl implements IManager{

  //定义people类数组存对象 index记录索引
  private People[] members;
  private int index=0;
  Scanner sc=new Scanner(System.in);

  //构造方法
    public ManagerImpl(){
      this(10);
    }
    public  ManagerImpl(int len){//动态定义数组长度
    members = new People[len];
  }
  public void start(){
    System.out.println("\n----------------------------------------");
    System.out.println("\n 当前人数："+index);
    System.out.println("\n 全部信息：");
    this.showInfo(this.findPeople());
    System.out.println("\n  选择操作：\n   --1.Add     --2.Find\n   --3.Delete  --4.Update");
    int sected=sc.nextInt();//储存选择操作类型
    System.out.println("\n----------------------------------------");
    System.out.println("  选择对象:  --1.Student      --2.Teacher");
    int id=sc.nextInt();//储存选择处理对象的类型
    System.out.println("\n----------------------------------------");
    switch(sected){
      case 1: this.add(id);break;
      case 2: System.out.print("  Pid: ");
              String c2 = sc.next();
              this.showInfo(this.findPeople(c2));
              break;
      case 3: System.out.print("  Pid: ");
              String c3 = sc.next();
              this.removePeople(c3);
              //print();
              break;
      case 4: System.out.print("  Pid: ");
              String c4 = sc.next();
              updatePeople(id,c4);
              break;
      default:System.out.println("  Error Id:"+id);
    }
    start();
  }
    //输入pid判断是否重复 如果重复就重写输入
    public void exist(String pid){
      boolean b=false;
      for(int i=0;i<this.index;i++){
        if(members[i].getPid().equals(pid)){
          b=true;
          break;
        }
      }//end for
      if(b){
        System.out.println("\n   --Error--Sid重复--\n  --重新输入--");
        this.start();
      }
    }

  //添加 
  public void add(int id){
    System.out.println("  --Add--  ");
    System.out.print("  Pid：");
    String  in1=sc.next();
    this.exist(in1);
    System.out.print("  Name：");
    String  in2=sc.next();
    System.out.print("  Age：");
    int  in3=sc.nextInt();
    System.out.print("  Home：");
    String  in4=sc.next();
    String in5=null;

    switch(id){
      case 1: System.out.print("  Major：");
              in5=sc.next();
              this.addPeople(new Student(in1,in2,in3,in4,in5));
              break;
      case 2: System.out.print("  Course：");
              in5=sc.next();
              this.addPeople(new Teacher(in1,in2,in3,in4,in5));
              break;
      default:System.out.println("  Error Id:"+id);
      }
  }//end add


  //将构造好的对象存到数组中
  public void addPeople(People p){

    //++数组扩容
    if (p!=null){
      this.members[index++]=p;
      System.out.println("\n    --Add Success--");
    }else{
      System.out.println("  Error add:"+p);
    }
  }//end addPeople

//查找指定编号的人
  public People findPeople(String pid){
    for(int i=0;i<this.index;i++){
      People p=this.members[i];
      if(p.getPid().equals(pid)){
        return p;
      }
    }
    System.out.println("  Error Pid:"+pid);
    return null;
  }//end findPeople

//查找所有人
  public People[] findPeople(){
    People[] result=null;//假定members中没有元素
    if(this.index>0){
      result =new People[this.index];
      for (int i=0;i<this.index;i++){
        result[i] =this.members[i];
      }//end for
    }//end if
    //System.out.println("--Find!!--");
    return result;
  }//end findPeople[]

  //删除操作
  public void removePeople(String pid){
    this.showInfo(this.findPeople(pid));
    for (int i=0;i<this.index;i++){
      if (this.members[i].getPid().equals(pid)){
        for(int j=i;i<this.index-1;i++){//将被删除位置后面的成员向前移动
          this.members[j]=this.members[j+1];
        }//end for2

        System.out.println("\n\n      --Removed!!--");

        break;
      }//end if
      if(i==this.index-1){
        System.out.println("   Null:"+pid);
      }//end if2
    }//end for1
    this.index--;//成员减少
  }//end


  //更改成员
  public void updatePeople(int id, String pid){
    if(this.index!=0){
      for(int i=0;i<this.index;i++){
          if(members[i].getPid().equals(pid)){
            System.out.print("  Pid：");
            String  in1=sc.next();
            this.exist(in1);
            System.out.print("  Name：");
            String  in2=sc.next();
            System.out.print("  Age：");
            int  in3=sc.nextInt();
            System.out.print("  Home：");
            String  in4=sc.next();
            String in5=null;
            switch(id){
              case 1: System.out.print("  Major：");
                      in5=sc.next();
                      this.members[i]=(new Student(in1,in2,in3,in4,in5));
                      break;
              case 2: System.out.print("  Course：");
                      in5=sc.next();
                      this.members[i]=(new Teacher(in1,in2,in3,in4,in5));
                      break;
              default:System.out.println("  Error Id:"+id);
              }
              break;
          }//end if

      }//end for
    }else{
      System.out.println("  Error Index:"+index);
    }

  }//end update


  //print
  public void showInfo(People...  peoples){
    if(peoples!=null && peoples.length>0){
      System.out.println("  Info:");
      for (People p :peoples){
        if(p!=null){

          System.out.print("      Pid: "+p.getPid()+"  Name: "+ p.getName()+" Age: "+p.getAge()+" Home: "+p.getHome());
          p.show();
        }else{
          System.out.println("  Error :Null");
        }

      }//end for each
    }//end if
  }//end showInfo

}
