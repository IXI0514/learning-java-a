package com.yunsi.work0805_Manager.service.impl;


import com.yunsi.work0805_Manager.beans.People;
import com.yunsi.work0805_Manager.beans.Student;
import com.yunsi.work0805_Manager.beans.Teacher;
import com.yunsi.work0805_Manager.service.IManager;

import java.util.Scanner;

public class ManagerImpl implements IManager{

  //����people���������� index��¼����
  private People[] members;
  private int index=0;
  Scanner sc=new Scanner(System.in);

  //���췽��
    public ManagerImpl(){
      this(10);
    }
    public  ManagerImpl(int len){//��̬�������鳤��
    members = new People[len];
  }
  public void start(){
    System.out.println("\n----------------------------------------");
    System.out.println("\n ��ǰ������"+index);
    System.out.println("\n ȫ����Ϣ��");
    this.showInfo(this.findPeople());
    System.out.println("\n  ѡ�������\n   --1.Add     --2.Find\n   --3.Delete  --4.Update");
    int sected=sc.nextInt();//����ѡ���������
    System.out.println("\n----------------------------------------");
    System.out.println("  ѡ�����:  --1.Student      --2.Teacher");
    int id=sc.nextInt();//����ѡ������������
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
    //����pid�ж��Ƿ��ظ� ����ظ�����д����
    public void exist(String pid){
      boolean b=false;
      for(int i=0;i<this.index;i++){
        if(members[i].getPid().equals(pid)){
          b=true;
          break;
        }
      }//end for
      if(b){
        System.out.println("\n   --Error--Sid�ظ�--\n  --��������--");
        this.start();
      }
    }

  //��� 
  public void add(int id){
    System.out.println("  --Add--  ");
    System.out.print("  Pid��");
    String  in1=sc.next();
    this.exist(in1);
    System.out.print("  Name��");
    String  in2=sc.next();
    System.out.print("  Age��");
    int  in3=sc.nextInt();
    System.out.print("  Home��");
    String  in4=sc.next();
    String in5=null;

    switch(id){
      case 1: System.out.print("  Major��");
              in5=sc.next();
              this.addPeople(new Student(in1,in2,in3,in4,in5));
              break;
      case 2: System.out.print("  Course��");
              in5=sc.next();
              this.addPeople(new Teacher(in1,in2,in3,in4,in5));
              break;
      default:System.out.println("  Error Id:"+id);
      }
  }//end add


  //������õĶ���浽������
  public void addPeople(People p){

    //++��������
    if (p!=null){
      this.members[index++]=p;
      System.out.println("\n    --Add Success--");
    }else{
      System.out.println("  Error add:"+p);
    }
  }//end addPeople

//����ָ����ŵ���
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

//����������
  public People[] findPeople(){
    People[] result=null;//�ٶ�members��û��Ԫ��
    if(this.index>0){
      result =new People[this.index];
      for (int i=0;i<this.index;i++){
        result[i] =this.members[i];
      }//end for
    }//end if
    //System.out.println("--Find!!--");
    return result;
  }//end findPeople[]

  //ɾ������
  public void removePeople(String pid){
    this.showInfo(this.findPeople(pid));
    for (int i=0;i<this.index;i++){
      if (this.members[i].getPid().equals(pid)){
        for(int j=i;i<this.index-1;i++){//����ɾ��λ�ú���ĳ�Ա��ǰ�ƶ�
          this.members[j]=this.members[j+1];
        }//end for2

        System.out.println("\n\n      --Removed!!--");

        break;
      }//end if
      if(i==this.index-1){
        System.out.println("   Null:"+pid);
      }//end if2
    }//end for1
    this.index--;//��Ա����
  }//end


  //���ĳ�Ա
  public void updatePeople(int id, String pid){
    if(this.index!=0){
      for(int i=0;i<this.index;i++){
          if(members[i].getPid().equals(pid)){
            System.out.print("  Pid��");
            String  in1=sc.next();
            this.exist(in1);
            System.out.print("  Name��");
            String  in2=sc.next();
            System.out.print("  Age��");
            int  in3=sc.nextInt();
            System.out.print("  Home��");
            String  in4=sc.next();
            String in5=null;
            switch(id){
              case 1: System.out.print("  Major��");
                      in5=sc.next();
                      this.members[i]=(new Student(in1,in2,in3,in4,in5));
                      break;
              case 2: System.out.print("  Course��");
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
