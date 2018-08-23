package com.yunsi.work0820_简单计时Gui;




import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * 计时的界面
 * @author ShenBL
 *
 */

public class MyFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private JButton sbt;
	private JButton bt;
	private JButton ebt;
	private JButton cbt;
	static JLabel date  = new JLabel();;
	static JLabel tlab;
	static JLabel tare;//记录
	
	
	public  MyFrame() {
		
		Demo();
	}
	
/*	Thread t = Thread.currentThread();
	System.out.println("main方法中执行线程是："+t);
	*/


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==sbt) {//开始计时
			System.out.println("开始计时");
			long start = System.currentTimeMillis();
			new MyAction().start(start);
			sbt.setEnabled(false);
			bt.setEnabled(true);
			ebt.setEnabled(true);
			cbt.setEnabled(true);
		}else if (e.getSource()==bt) {//记次
			System.out.println("记一次！");
			new MyAction().print();
			
		}else if (e.getSource()==ebt) {//停止
			new MyAction().stop();
			
		}else if (e.getSource()==cbt) {//继续
			new MyAction().con();
		}
		
	}
	
	
	
	
	
	private  void Demo(){
		//fanew JFrame("秒表")
		new MyAction().CurrDate();
		System.out.println("初始化......");
		
		sbt = new JButton("启动");
		bt = new JButton("计次");
		ebt = new JButton("暂停");
		cbt = new JButton("继续");
		tlab = new JLabel("计时：");
		tare = new JLabel();
		
		bt.setEnabled(false);
		ebt.setEnabled(false);
		cbt.setEnabled(false);
		
		this.setBounds(400, 250, 550, 250);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sbt.addActionListener(this);
		bt.addActionListener(this);
		ebt.addActionListener(this);
		cbt.addActionListener(this);
		//部件位置大小
		date.setBounds(150, 10, 200, 20);
		tlab.setBounds(200, 40, 200, 20);
		tare.setBounds(50, 70, 400, 70);
		sbt.setBounds(40, 160, 80, 40);
		bt.setBounds(130, 160, 80, 40);
		ebt.setBounds(220, 160, 80, 40);
		cbt.setBounds(310, 160, 80, 40);
		//
		
		//添加
		Container container = this.getContentPane();
		container.add(date);
		container.add(tlab);
		container.add(tare);
		container.add(sbt);
		container.add(bt);
		container.add(ebt);
		container.add(cbt);
		
		this.setVisible(true);
		
		
		
	}
	
	
	
	
}
