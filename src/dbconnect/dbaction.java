package dbconnect;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class dbaction {
	public static Connection getConnection(){
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
	public static void main(String[] args) throws IOException {
		Connection conn=getConnection();
		int i=1;
		try{
			System.out.println("显示图片");
			PreparedStatement s = null;
	//		String sql = "select ipicture FROM all_shop limit 0,2";
//			String sql = "select iname FROM all_shop ";
//			String sql1="delete from all_shop where iname='东京周游卡' ";
//			String sql2="insert into shop values()"
			try {
//			String sql1="UPDATE customers SET sell = " + 
//					" (SELECT sell FROM (SELECT sell FROM customers WHERE customers_name ='沉默的123') t1);";
//			String sql2="UPDATE `customers` SET `sell`=REPLACE(`sell`,'hh','');";
			String sql3="insert into shop values(?,?,?,?)";
			InputStream in=ImageUtil.getImageByte("D:/PS修图/cl.jpg");
			s=conn.prepareStatement(sql3);
			s.setString(1, "用券");
			s.setString(2, "欢乐长隆旅游卡");
			s.setBinaryStream(3, in, in.available());
			s.setString(4, "沉默的123");
			s.execute();
			}
			catch (SQLException | FileNotFoundException e){
				e.printStackTrace();
			}
			System.out.println("删除成功");
//			ResultSet re=s.executeQuery(sql);
//			while(re.next()) {	
	//		System.out.println(re.getString(1)+"  结果");
//			String r=re.getString(1);	
//			JFrame jf=new JFrame();
//	        JPanel jp=new JPanel(){
//	        public void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                ImageIcon ii = new ImageIcon(r);
//                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
//            }
//        };
//            jf.add(jp);
//			jf.setSize(200, 200);
//			jf.setVisible(true);
//			i++;
//			}	
//			re.close();
			s.close();
			conn.close();
			
		}
		catch(SQLException e){
			
		}
	}
	
}
