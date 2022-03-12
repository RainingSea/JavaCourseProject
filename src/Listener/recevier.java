package Listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import dbconnect.db;

public class recevier implements ActionListener {
	static JTextArea jta;
	static Box vb;
	static JFrame frame;
	static JTextArea ta;
	static JTextField tf;
	private static PrintWriter printWriter;
	private static BufferedReader bufferedReader;
	private Socket socket = null;
	int who;
   
	public void actionPerformed(ActionEvent e) {     
		
		frame = new JFrame();
		frame.setTitle("我的留言");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);

		// 设置整个窗体布局
		JLabel jll=new JLabel();
		jll.setText("来自200元Pr学习卡");
		jll.setForeground(Color.red);
		jll.setBounds(0, 0, 300,50);
		vb = Box.createVerticalBox();
		JScrollPane sc = new JScrollPane(vb);
		
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// 设置滚动条
		Border border = BorderFactory.createBevelBorder(1);
		border = BorderFactory.createLineBorder(Color.black, 5, false);
		Border border5 = BorderFactory.createTitledBorder(border);
		vb.setBorder(border5);
		sc.setBounds(0, 50, 300, 300);

		tf = new JTextField();
		tf.setBounds(0, 350, 300, 150);

		// 建出初始窗口

		InetAddress addr;

		try {
			Socket socket;
			addr = InetAddress.getByName(null);
			socket = new Socket(addr, 8888);
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
	
		tf.addFocusListener(new JTextFieldListener(tf, "请在此输入内容"));
		JButton jb = new JButton("离线回溯");
		jb.setBounds(0, 500, 300, 70);
		jb.setBackground(Color.white);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tf.getText().isEmpty()) {
					JOptionPane.showMessageDialog(recevier.frame, "请输入内容！");
				} else {
					printWriter.println(tf.getText());
					System.out.println(tf.getText()+"asasas");
					JLabel jl = new JLabel(tf.getText());
					jl.setFont(new java.awt.Font("Dialog", 1, 15));
					jl.setForeground(Color.blue);
					vb.add(jl);
					frame.validate();
					tf.setText("");
				}
			}
		});
		frame.add(jll);
		frame.add(sc);
		frame.add(tf);
		frame.add(jb);
		frame.setVisible(true);
		System.out.println("进行到这里！+111");
		 String msg = null;//服务器发过来的信息
         try {
						msg = bufferedReader.readLine();
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
		} 
		System.out.println(msg);
		int ko=0;
		String input = "";
		while(ko<msg.length()) {
			if(msg.charAt(ko)!='$')
			input+=msg.charAt(ko);
			else {
		JLabel jl=new JLabel();
		jl.setFont(new java.awt.Font("Dialog", 1, 15));
		jl.setForeground(Color.red);
		jl.setText("对方: "+input);
		System.out.println(input+"ssss");
		vb.add(jl);
		frame.validate();
		input="";
		}
			ko++;
		}
		System.out.println("进行到这里！+222");
	}//发送端界面生成，只有发送方法
	}

