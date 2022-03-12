package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import window.itemwin;



public class itemlis implements ActionListener {
    String name;
	String path;
    
	public itemlis(String name,String path) {
		this.path=path;
		this.name=name;
	}
      
	@Override
	public void actionPerformed(ActionEvent e) {
		itemwin jf=new itemwin(name,path);
		
	}

}
