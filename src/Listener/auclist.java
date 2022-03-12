package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.sun.jdi.connect.spi.Connection;

import dbconnect.db;
import window.paicli;

public class auclist extends JFrame implements ActionListener {

	public static void main(String[] args) {
		JFrame frame=new JFrame();
	    frame.setTitle("Next Innovation");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(500, 400);
	    frame.setLocationRelativeTo(null);
	    
		Box vb=Box.createVerticalBox();
		java.sql.Connection con=db.getConshop();
	    try {
			Statement sta=con.createStatement();
			String sql="select shop,label from auction";
			ResultSet set=sta.executeQuery(sql);
			String s,s1;
			while(set.next()) {
				s=set.getString(1);
				JButton jb=new JButton(s);
				s1=set.getString(2);
				ActionListener auc=new loadauc("D:/PS修图/原图/ser0.PNG",s1);
				jb.addActionListener(auc);
				vb.add(jb);				
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	   frame.add(vb);
	   frame.setVisible(true);  

	}

	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}

}
