package com.yunsi.work0818_GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class WBframe extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JLabel label;
	private JButton button;
	private JButton button_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WBframe frame = new WBframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WBframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setBounds(107, 54, 54, 26);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(171, 55, 168, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label = new JLabel("\u5BC6\u7801");
		label.setBounds(107, 114, 54, 26);
		contentPane.add(label);
		
		button = new JButton("\u767B\u9646");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane, textField.getText()+"\n"+String.valueOf(passwordField.getPassword()), "tips", JOptionPane.PLAIN_MESSAGE);
				System.out.println();
			}
		});
		button.setBounds(127, 167, 93, 23);
		contentPane.add(button);
		
		button_1 = new JButton("\u6CE8\u518C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showInputDialog(contentPane, message, title, messageType)
			}
		});
		button_1.setBounds(270, 167, 93, 23);
		contentPane.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(171, 114, 168, 24);
		contentPane.add(passwordField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
