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
import java.net.UnknownHostException;

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

public class talkleft implements ActionListener {
	static JTextArea jta;
	static Box vb;
	static JFrame frame;
	static JTextArea ta;
	static JTextField tf;
	private static PrintWriter printWriter;
	int who;
	public talkleft(int i) {
		if (i == 0)
			who = 0;
		else
			who = 1;
	}

	public void actionPerformed(ActionEvent e) {     
		if(who==0) {
		frame = new JFrame();
		frame.setTitle("聊天");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);

		// 设置整个窗体布局
		vb = Box.createVerticalBox();
		JScrollPane sc = new JScrollPane(vb);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// 设置滚动条
		Border border = BorderFactory.createBevelBorder(1);
		border = BorderFactory.createLineBorder(Color.black, 5, false);
		Border border5 = BorderFactory.createTitledBorder(border);
		vb.setBorder(border5);
		sc.setBounds(0, 0, 300, 350);

		tf = new JTextField();
		tf.setBounds(0, 350, 300, 150);

		// 建出初始窗口

		InetAddress addr;

		try {
			Socket socket;
			addr = InetAddress.getByName(null);
			socket = new Socket(addr, 8888);
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

		tf.addFocusListener(new JTextFieldListener(tf, "请在此输入内容"));
		JButton jb = new JButton("发送");
		jb.setBounds(0, 500, 300, 70);
		jb.setBackground(Color.white);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tf.getText().isEmpty()) {
					JOptionPane.showMessageDialog(talkleft.frame, "请输入内容！");
				} else {
					printWriter.println(tf.getText());
					JLabel jl = new JLabel(tf.getText());
					jl.setFont(new java.awt.Font("Dialog", 1, 15));
					jl.setForeground(Color.blue);
					vb.add(jl);
					frame.validate();
					tf.setText("");
				}
			}
		});
		frame.add(sc);
		frame.add(tf);
		frame.add(jb);
		frame.setVisible(true);
	}//发送端界面生成，只有发送方法
	
	else {
		frame = new JFrame();
		frame.setTitle("查看留言");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);

		// 设置整个窗体布局
		vb = Box.createVerticalBox();
		JScrollPane sc = new JScrollPane(vb);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// 设置滚动条
		Border border = BorderFactory.createBevelBorder(1);
		border = BorderFactory.createLineBorder(Color.black, 5, false);
		Border border5 = BorderFactory.createTitledBorder(border);
		vb.setBorder(border5);
		sc.setBounds(0, 0, 300, 350);

		tf = new JTextField();
		tf.setBounds(0, 350, 300, 250);
		
		frame.add(sc);
		frame.add(tf);
		frame.setVisible(true);
	}
	}}
//		Socket socket = null;
//		
//		InetAddress addr = null;
//		try {
//			addr = InetAddress.getByName(null);
//		} catch (UnknownHostException e3) {
//			// TODO 自动生成的 catch 块
//			e3.printStackTrace();
//		}
//		try {
//			socket = new Socket(addr, 8888);
//		} catch (IOException e3) {
//			// TODO 自动生成的 catch 块
//			e3.printStackTrace();
//		}
//		BufferedReader bufferedReader = null;
//		try {
//			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		} catch (IOException e3) {
//			// TODO 自动生成的 catch 块
//			e3.printStackTrace();
//		}
//		 try {
//	         String msg;//服务器发过来的信息
//	         while ((msg = bufferedReader.readLine()) != null) {
//	             System.out.println(msg);
//	         }
//	     } catch (IOException e2) {
//	         System.out.println("警告：断开连接！");
//	         try {
//	             if (!socket.isClosed()) {
//	                 socket.close();
//	             }
//	         } catch (IOException e1) {
//	             System.out.println("读取线程：关闭socket出现错误");
//	         }
//	     }
//	     System.exit(1);
//		
//	}
//	}
//}



