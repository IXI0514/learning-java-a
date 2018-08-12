package com.yunsi.work0805_Manager.service;
import com.yunsi.work0805_Manager.beans.*;


public interface IManager {

  public void start();
  public void add(int id);
  public void addPeople(People p);
  public People findPeople(String pid);
  public People[] findPeople();
  public void removePeople(String pid);
  public void updatePeople(int id, String pid);
  public void showInfo(People...  peoples);

}
