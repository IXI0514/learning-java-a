package com.yunsi.work0805_Manager;
import com.yunsi.work0805_Manager.service.impl.*;

public class Index{
  public static void main (String [] args){
		ManagerImpl m = new ManagerImpl(10);//分配10个空间
    m.start();//开始
	}
}
