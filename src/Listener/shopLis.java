package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import window.list;
public class shopLis implements ActionListener {
	String name,born;
	public shopLis(String a,String b) {
		name=a;born=b;
	}
	public void actionPerformed(ActionEvent e) {
	try {
		list shoplist =new list(name,born);
	} catch (IOException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}
	}

}
