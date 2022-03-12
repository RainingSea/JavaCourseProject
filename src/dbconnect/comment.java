package dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

public class comment {
	public void insert(int id,String str){
		 Connection conn = null;
		 try {
			 	//初始化驱动类com.mysql.jdbc.Driver
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
			 + "shopitem?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC","root","zyhyrh");
	           
	        } catch (ClassNotFoundException e) { 				
	            e.printStackTrace();
	        }catch (SQLException e) {							
	            e.printStackTrace();
	        }
		   String sql="insert into comment() values(?,?)";
			try{
				System.out.println("准备插入评论");
				PreparedStatement ps = null;
				ps = (PreparedStatement) conn.prepareStatement(sql);
				String value1 =Integer.toString(id);
				ps.setString(1,value1); //给占位符赋值
    			ps.setString(2, str); //给占位符赋值
    			ps.executeUpdate();	
				ps.close();
				conn.close();	
			
			}
			catch(SQLException e){	
			}	
		}
}

