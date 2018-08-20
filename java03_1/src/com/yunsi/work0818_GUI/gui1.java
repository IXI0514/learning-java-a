package com.yunsi.work0818_GUI;

import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.bind.Marshaller.Listener;


public class gui1 {
	public static void main(String[] args) {
		//newFrame();
		newFrame2();
		
		
	}

	private static void newFrame2() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("复选");
		frame.setLayout(new FlowLayout());
		frame.setBounds(300, 300, 400, 300);
		JPanel j1 = new JPanel();
		j1.setBounds(2, 2, 300, 40);
		frame.add(j1);
		String[] strings = {"test1","test2","test3","test4"};
		JCheckBox[] boxs = new JCheckBox[4];
		for(int i= 0; i<boxs.length;i++) {
			boxs[i]=new JCheckBox(strings[i]);
			j1.add(boxs[i]);
		}
		
		Button button = new Button("全选");
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button) {
					
				}
				
			}
		};
				
		
		button.addActionListener(listener);
	
		frame.setVisible(true);
	}

	private static void newFrame() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Frame~");
		frame.setLayout(new FlowLayout());
		frame.setSize(300, 500);
		//按钮
		JButton button = new JButton("登陆");
		//button.setBounds(100, 160, 80, 20);

		JButton button2 = new JButton("注册");
		//button2.setBounds(100, 190, 80, 20);
		//文本
		JTextField textField = new JTextField(20);
		//textField.setBounds(100, 100, 120, 20);
		JPasswordField passwordField = new JPasswordField(20);
		//passwordField.setBounds(100,130,120,20);
		//下拉
		String[] objects = new String[]{"box1","box2","box3"};
		JComboBox<String> box = new JComboBox<String>(objects);
		//文本域
		 JTextArea area = new JTextArea(5, 20);
		//事件
		ActionListener actionListener = new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(e);
//				System.out.println(e.getActionCommand());
//				System.out.println(e.getID());
//				System.out.println(e.getModifiers());
				System.out.println(e.getSource());
				System.out.println(button);
				Object ob = e.getSource();
				if (ob==button) {
					String text1 = textField.getText();
					char[] cs =passwordField.getPassword();
					String text2 = String.valueOf(cs);
					Object text3 = box.getSelectedItem();
					JOptionPane.showMessageDialog(frame,"用户名："+text1 +"\n密码："+text2+"\n选择："+text3, "登陆信息",JOptionPane.PLAIN_MESSAGE);
				}else {
					System.out.println("2");
				}
			}
		};
		button.addActionListener(actionListener);
		button2.addActionListener(actionListener);
		
		//添加到容器中
		Container container = frame.getContentPane();
		JPanel j0 = new JPanel();
		container.add(j0);
		j0.add(new JLabel("用户名"));
		j0.add(textField);
		JPanel j1 = new JPanel();
		container.add(j1);
		j1.add(new JLabel("密码名"));
		j1.add(passwordField);
		
		container.add(box);
		
		JPanel jPanel = new JPanel();
		container.add(jPanel);
		jPanel.add(button);
		jPanel.add(button2);
		
		

		frame.setVisible(true);
	}
	
	
}
