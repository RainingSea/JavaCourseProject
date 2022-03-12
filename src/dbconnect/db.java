package dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class db {
	//得到shopitem的数据库
	public static Connection getConshop(){
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
		 return conn;
	}
	
	//得到user的数据库
	public static Connection getConcus(){
		Connection conn = null;
		 try {
			 	//初始化驱动类com.mysql.jdbc.Driver
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
			 + "customers?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC","root","zyhyrh");
	           
	        } catch (ClassNotFoundException e) { 				
	            e.printStackTrace();
	        }catch (SQLException e) {							
	            e.printStackTrace();
	        }
		 return conn;
	}
}	
	

