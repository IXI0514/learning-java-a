package com.yunsi.work0805_Manager;
import com.yunsi.work0805_Manager.service.impl.*;

public class Index{
  public static void main (String [] args){
		ManagerImpl m = new ManagerImpl(10);//����10���ռ�
    m.start();//��ʼ
	}
}
