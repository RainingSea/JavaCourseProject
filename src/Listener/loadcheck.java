package Listener;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnect.db;
import window.list;

public class loadcheck {
	public loadcheck(String name,String password) throws IOException {
		System.out.println(name);
		java.sql.Connection con=db.getConcus();
		try {
		String sql = "select * FROM customers where customers_name=? ;";
		PreparedStatement s = con.prepareStatement(sql);
		s.setString(1, name);		
		ResultSet re=s.executeQuery();
		while(re.next()) {
		String pass=re.getString(2);
		if(pass.equals(password)) {
			System.out.println("登录成功");
		    list list=new list(name,"");
		}
		else {
			System.out.println("密码错误");
		
		}
		}
		s.close();
		re.close();
		con.close();
		}
		catch(SQLException e1){
			e1.printStackTrace();
		}
	}
}

