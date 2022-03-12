package projectmain;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.jdi.connect.spi.Connection;

import Listener.Listener;
import Listener.Listenerin;
import Listener.loadcheck;
import Listener.shopLis;
import dbconnect.db;

public class framemain {
	public static void main(String[] args) {
		//登录界面构建
		JFrame frame=new JFrame();
	    frame.setTitle("Next Innovation");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(500, 400);
	    frame.setLocationRelativeTo(null);
	    
	    Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		
		//左端欢迎文字
		JLabel label1 = new JLabel("<html><p>Welcome to</p></p>Next innovation!</p></html>");
		content.add(label1,BorderLayout.WEST);
		JLabel label2 = new JLabel("感谢使用本系统");
		content.add(label2,BorderLayout.EAST);
		
		//中央面板
		JPanel j=new JPanel();
	    j.setLayout(new GridLayout(4, 1));
	    //登录按钮
	    JTextField jtf1=new JTextField(20);
	    JLabel jll1=new JLabel("用户名");
	    Font fu=new Font("微软雅黑",Font.BOLD,24);
	    jll1.setFont(fu);
	    JPanel jp1=new JPanel();
	    jp1.add(jll1);jp1.add(jtf1);
	    
	    JTextField jtf2=new JTextField(20);
	    JLabel jll2=new JLabel("   密码");
	    jll2.setFont(fu);
	    JPanel jp2=new JPanel();
	    jp2.add(jll2);jp2.add(jtf2);
	    
	    JButton b1=new JButton("登录");
	    b1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					loadcheck ld=new loadcheck(jtf1.getText(),jtf2.getText());
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
	    	} 	
	    		{
	    	}
	    });
	    Font f=new Font("微软雅黑",Font.BOLD,24);//设置字体格式
	    b1.setFont(f); 
	    b1.setBackground(Color.white);//背景色
//      shopLis lis=new shopLis(jtf1.getText(),jtf2.getText());
	    
	  
	    //注册按钮
	    JButton b2=new JButton("注册");
	    Font f1=new Font("微软雅黑",Font.BOLD,24);//设置字体格式
	    b2.setFocusPainted(false);//去边框（文字）
	    b2.setFont(f1); 
	    b2.setBackground(Color.white);//背景色
	    Listenerin lis11=new Listenerin();
	    b2.addActionListener(lis11);
	    //添加4行组件
	    j.add(jp1);j.add(jp2);j.add(b1);j.add(b2);
	    content.add(j);
		//上方菜单
		JMenuBar mBar = new JMenuBar();
		JMenu memoMenu = new JMenu("账号选项");
		JMenuItem m; 
		m = new JMenuItem("无法登陆？"); 
		ActionListener lis1=new Listener("a","b");
		m.addActionListener(lis1);
		memoMenu.add(m); 
		m = new JMenuItem("无法注册？"); 
		ActionListener lis2=new Listener("a","b");
		m.addActionListener(lis2);
		memoMenu.add(m);
		mBar.add(memoMenu);
	
		
		//菜单条目
		JMenu memoMenu1 = new JMenu("管理项目");
		JMenuItem m1; 
		m1 = new JMenuItem("退出账号"); 
		m1.addActionListener(lis1);
		memoMenu1.add(m1); 
		m1 = new JMenuItem("切换账号"); 
		m.addActionListener(lis2);
		memoMenu1.add(m1);
		mBar.add(memoMenu1);
		mBar.setVisible(true);
		
        content.add(mBar,BorderLayout.NORTH);
      
		frame.setVisible(true);
	}
	class check implements ActionListener{
		String name,password;
		
        public check(String name,String password) {
        	this.name=name;
        	this.password=password;
        }
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			try {
				loadcheck ld=new loadcheck(name,password);
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
	}
}
}


