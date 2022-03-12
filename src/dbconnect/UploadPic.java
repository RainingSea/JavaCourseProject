package dbconnect;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.protocol.Resultset;

import projectmain.photo;

public class UploadPic {
    private static final String  URL="jdbc:mysql://127.0.0.1:3306/uploadpic";
    private static final String USER="root";
    private static final String PASS="1234";
    public static void main(String[] args) throws SQLException, IOException {
     java.sql.Connection con=null;       
        	try {
			 	//初始化驱动类com.mysql.jdbc.Driver
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
			 + "shopitem?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC","root","zyhyrh");
	           
	        } catch (ClassNotFoundException e) { 				
	            e.printStackTrace();
	        }catch (SQLException e) {							
	            e.printStackTrace();
	        }
        	
//        	String sql = "SELECT * FROM shop "
//					+ "WHERE shop = '200元Pr学习卡' ";
//        	PreparedStatement preparedstatement=null; 
//			preparedstatement = con.prepareStatement(sql);		
//			ResultSet resultset=null;
//			resultset = preparedstatement.executeQuery();
//			int j=1;
//			while(resultset.next()){
//				String name = resultset.getString("shop");
//				System.out.println(  " shopname= " + name  
//						);
//				java.sql.Blob picture = resultset.getBlob("picture");
//				InputStream in = picture.getBinaryStream();
//				OutputStream out = new FileOutputStream("D:/PS修图/out"+"ll"+".PNG");//字节流转化的目标路径
//				byte[] buffer = new byte[1024];
//				int i = 0;
//				while((i = in.read(buffer)) != -1){
//					out.write(buffer, 0, i);
//				}
//				j++;
//				System.out.println(j);
//				out.close();
//				in.close();
//			}	
//		resultset.close(); preparedstatement.close();con.close();
//		System.out.println("Hello, World!");
//          }
//    }
            photo p=new photo("D:/PS修图/原图/日本街道1.jpg");
        	InputStream in=p.get();
//            InputStream in=ImageUtil.getImageByte("D:/PS修图/日本街道1.jpg");
            String sql="insert into shop values(?,?,?,?)";
            //指定参数位置
            PreparedStatement ptmt=con.prepareStatement(sql);
            ptmt.setString(1,"用券");
            ptmt.setString(2,"欢乐长隆旅游卡");
            ptmt.setBinaryStream(3, in,in.available());
            ptmt.setString(4,"不绝");
            
            ptmt.execute();
            System.out.println("Hello, world!");
            
            ptmt.close();
            in.close();    
            con.close();
               
    }
}




 