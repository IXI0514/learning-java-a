package com.yunsi.work0820_�򵥼�ʱGui;




import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * ��ʱ�Ľ���
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
	static JLabel tare;//��¼
	
	
	public  MyFrame() {
		
		Demo();
	}
	
/*	Thread t = Thread.currentThread();
	System.out.println("main������ִ���߳��ǣ�"+t);
	*/


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==sbt) {//��ʼ��ʱ
			System.out.println("��ʼ��ʱ");
			long start = System.currentTimeMillis();
			new MyAction().start(start);
			sbt.setEnabled(false);
			bt.setEnabled(true);
			ebt.setEnabled(true);
			cbt.setEnabled(true);
		}else if (e.getSource()==bt) {//�Ǵ�
			System.out.println("��һ�Σ�");
			new MyAction().print();
			
		}else if (e.getSource()==ebt) {//ֹͣ
			new MyAction().stop();
			
		}else if (e.getSource()==cbt) {//����
			new MyAction().con();
		}
		
	}
	
	
	
	
	
	private  void Demo(){
		//fanew JFrame("���")
		new MyAction().CurrDate();
		System.out.println("��ʼ��......");
		
		sbt = new JButton("����");
		bt = new JButton("�ƴ�");
		ebt = new JButton("��ͣ");
		cbt = new JButton("����");
		tlab = new JLabel("��ʱ��");
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
		//����λ�ô�С
		date.setBounds(150, 10, 200, 20);
		tlab.setBounds(200, 40, 200, 20);
		tare.setBounds(50, 70, 400, 70);
		sbt.setBounds(40, 160, 80, 40);
		bt.setBounds(130, 160, 80, 40);
		ebt.setBounds(220, 160, 80, 40);
		cbt.setBounds(310, 160, 80, 40);
		//
		
		//���
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
