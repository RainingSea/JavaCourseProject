package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;

import window.user;

public class Listener implements ActionListener {
	String name,born;
	public Listener(String a,String b) {
		name=a;born=b;
	}
	public void actionPerformed(ActionEvent e) {	
		try {
			user us=new user(name,born);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}			
	}
}
