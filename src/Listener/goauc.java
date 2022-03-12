package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbconnect.ImageUtil;
import dbconnect.db;
import window.paimai;

public class goauc implements ActionListener {
	String path=null;
	String la=null;
	String na=null;
    public goauc() {
    }
	public void actionPerformed(ActionEvent e) {
		path=up.lpicture.getText();
	    la=up.jtu2.getText();
	    na=up.jtu1.getText();
	    java.sql.Connection con=db.getConshop();
	    try {
	    	InputStream in=ImageUtil.getImageByte(path);
			
			String sql="insert into auction values(?,?)";
			PreparedStatement sta=(PreparedStatement) con.prepareStatement(sql);
			sta.setString(1, na);
			sta.setBinaryStream(2, in, in.available());
            sta.execute();
	    }
	    catch(IOException | SQLException e1) {
			e1.printStackTrace();
		}
	try {
		paimai auc=new paimai(path,la);
	} catch (IOException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}
	}

}
