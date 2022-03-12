package Listener;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import dbconnect.db;

public class Listenerin implements ActionListener {
    static JTextField text1;static JTextField text2;
	public void actionPerformed(ActionEvent e) {
		
		//注册界面
		JFrame frame=new JFrame();
	    frame.setTitle("注册界面");
	    frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Exit the application and close the window
	    frame.setSize(400, 300);
	    Container content = frame.getContentPane();
		GridBagLayout gbl = new GridBagLayout();
		content.setLayout(gbl);	
		GridBagConstraints gbs = new GridBagConstraints();
		
		//设置账号
		JLabel label1 = new JLabel("账号名称");
		content.add(label1);
		gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=0;gbs.gridy=0;
		gbl.setConstraints(label1, gbs);

		text1=new JTextField(10);
		content.add(text1);
		gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=1;gbs.gridy=0;
		gbl.setConstraints(text1, gbs);
		
	    //设置密码
		JLabel label2 = new JLabel("密码");
		content.add(label2);
		gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=0;gbs.gridy=1;
		gbl.setConstraints(label2, gbs);
		
		 text2=new JTextField(10);
		content.add(text2);
		gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=1;gbs.gridy=1;
		gbl.setConstraints(text2, gbs);
		
		JButton b=new JButton("完成注册");
		ActionListener register=new registerLis();
		b.addActionListener(register);
		content.add(b);
		gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=1;gbs.gridy=2;
		gbl.setConstraints(b, gbs);
		
		frame.setVisible(true);
	}
}
class registerLis implements ActionListener{
	public void actionPerformed(ActionEvent e) {
	String s1=Listenerin.text1.getText();
	String s2=Listenerin.text2.getText();
	String sql="insert into customers(customers_name,pass) values(?,?)";
    Connection conn=null;
    PreparedStatement ps=null;
	try{
		conn = db.getConcus();
		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1, s1); //给占位符赋值
		ps.setString(2, s2); //给占位符赋值
		ps.executeUpdate();			//执行
	}catch(SQLException e1){
		e1.printStackTrace();
	}
	finally{
		try {
		ps.close();
		conn.close();	//必须关闭
	}
		catch(SQLException e1){
			e1.printStackTrace();}
	}
	System.out.println("执行插入语句成功");
	}          		
	}

