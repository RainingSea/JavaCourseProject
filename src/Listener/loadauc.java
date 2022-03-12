package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import window.paicli;

public class loadauc implements ActionListener {
	String path=null;
	String la=null;
    public loadauc(String s,String s1) {
    	path=s;
    	la=s1;
    }
	public void actionPerformed(ActionEvent e) {
	try {
		paicli auc=new paicli(path,la);
	} catch (IOException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}
	}

}
