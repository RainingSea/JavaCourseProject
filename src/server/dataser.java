package server;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import dbconnect.ImageUtil;
import dbconnect.db;
import projectmain.shop;

public class dataser {
	public static final int PORT = 8999;
	static Socket[] sk;
	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server Started，正在监测8999端口");
		sk=new Socket[6];
		int i=0;
		try {
			while (true) {
				sk[i] = s.accept();
				System.out.println(sk[i].getLocalAddress());
				try 
				{
					new moredata(sk[i]);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}	
				i++;
		                 }		
		} 
		finally {
			s.close();
		}
	}
}

class moredata extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private DataInputStream is; 
	 Connection conn=null;
     PreparedStatement ps=null;
     int photo=0;
	public moredata(Socket s) throws IOException {
			socket = s;	
			start(); // Calls run()
			System.out.println("已创建新连接，ip地址为："+socket.getInetAddress());
			System.out.println("一名客户端连接");
		}
	public void run() {
		try {	
			
			DataInputStream din=new DataInputStream(socket.getInputStream());
			char a;
			String type=din.readUTF();
		    a=type.charAt(0);
			switch(a) {
			case 'i': 
		                  DataOutputStream dout=new DataOutputStream(socket.getOutputStream()); 
		                  dout.writeUTF("server: -i am greeting server");
		                  dout.writeUTF("server:- hi! hello client");
		                  
		                  System.out.println(din.readUTF());
		                  System.out.println(din.readUTF());
		                  
		                  String kl1=din.readUTF();
		                  String kl2=din.readUTF();
		                  String kl3=din.readUTF();

		 
		                  BufferedImage img=ImageIO.read(ImageIO.createImageInputStream(socket.getInputStream()));
		                  
		                  ImageIO.write(img,"jpg",new File("D:/PS修图/out"+photo+".jpg")); 
		                 
		                  System.out.println("Image received!!!!"); 
				          insert(kl1,kl2,kl3,"D:/PS修图/out"+photo+".jpg");
				          photo++;
				          break;
				case 'd': String shop=din.readUTF();
				          String name=din.readUTF();
				          System.out.println("阔以");
					      delete(shop,name);
					      break;	
				case 'c': String item=din.readUTF();
				          String comment=din.readUTF();
				          comment(item,comment);
				          break;
				case 'f': String shop1=din.readUTF();
		                  String name1=din.readUTF();
		                  System.out.println("阔以");
			              delete2(shop1,name1);
			              break;	
				case 'm': String sh=din.readUTF();
		                  String na=din.readUTF();
					      addbuy(sh,na);
					      break;	

		}
		}
//				System.out.println("Echoing: " + str);

		  catch (IOException e4) {
			e4.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Socket not closed");
			}
		}
	}
	
	//服务器的4个方法
public void insert(String a,String b,String c,String d) {
	try{	
		FileInputStream input=ImageUtil.getImageByte(d);
		String sql="insert into shop values(?,?,?,?)";
		conn = db.getConshop();
		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1, a); //给占位符赋值
		ps.setString(2, b); 
		ps.setBinaryStream(3, input,input.available());//给占位符赋值
		ps.setString(4, c);    			
		ps.executeUpdate();	
		System.out.println("插入完毕");//执行
		
		Connection con1 = db.getConcus();
		String sql1="select sell from customers where customers_name=? ";
		ps = (PreparedStatement) con1.prepareStatement(sql1);
//		ps.setString(1, b+"$");
		ps.setString(1, c);
		ResultSet re=ps.executeQuery();
		String s=null;
		while(re.next())
			s=re.getString(1);
		System.out.println(s);
		if(s==null)
			s="";
		String sql2="UPDATE customers SET sell = ? where customers_name=?; ";
		ps = (PreparedStatement) con1.prepareStatement(sql2);
		System.out.println(c);
		ps.setString(1, s+b+"$");
		ps.setString(2, c);
		ps.execute();
		
		Connection con2 = db.getConshop();
		String sql3="insert into comment values(?,'欢迎前来评论$') ";
		ps = (PreparedStatement) con2.prepareStatement(sql3);
		
		ps.setString(1, b);
		ps.execute();
		
		System.out.println("再次插入完毕");
	}catch(SQLException | IOException e1){
		e1.printStackTrace();
	}
	} 

public void delete(String a,String b) {
	try{
		System.out.println(a);
		String sql="delete from shop where shop =?";
		conn = db.getConshop();
		ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1, a); //给占位符赋值   			
		ps.executeUpdate();			//执行
		
        String sql2="UPDATE `customers` SET `sell`=REPLACE(`sell`,?, ?);";
        conn = db.getConcus();
        ps = (PreparedStatement) conn.prepareStatement(sql2);
        ps.setString(1, a+"$"); 
        ps.setString(2, "");//给占位符赋值  
		ps.executeUpdate();			//执行
		
		String sql3="UPDATE `customers` SET `buy`=REPLACE(`buy`,?, ?);";
        ps = (PreparedStatement) conn.prepareStatement(sql3);
        ps.setString(1, a+"$"); 
        ps.setString(2, "");//给占位符赋值  
		ps.executeUpdate();			//执行
	}catch(SQLException e1){
		e1.printStackTrace();
	}
	finally{
		try {
		ps.close();
		conn.close();	//必须关闭
	}
		catch(SQLException e1){
			e1.printStackTrace();}
	}
	System.out.println("执行删除语句成功");
	}          
public void delete2(String a,String b) {
	try{
		String sql="UPDATE `customers` SET `buy`=REPLACE(`buy`,?,?) where customers_name='不绝';";
        conn = db.getConcus();
        ps = (PreparedStatement) conn.prepareStatement(sql);
        ps.setString(1, a+"$"); 
        ps.setString(2, "");//给占位符赋值  
		ps.executeUpdate();			//执行
		
	}catch(SQLException e1){
		e1.printStackTrace();
	}
	finally{
		try {
		ps.close();
		conn.close();	//必须关闭
	}
		catch(SQLException e1){
			e1.printStackTrace();}
	}
	System.out.println("执行删除语句成功");
	}          

public void comment(String na,String str){
	System.out.println("这是在进行评论");
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

	   String sql="UPDATE comment SET comment= CONCAT(comment,?)  WHERE shop=?";
		try{		
			System.out.println("准备插入评论zzr");
			PreparedStatement ps = null;
			System.out.println(str);
			System.out.println(na);
			ps = (PreparedStatement) conn.prepareStatement(sql);
			System.out.println("插入完成！");
			try {
			ps.setString(1,str+"$"); //给占位符赋值
		    ps.setString(2, na); //给占位符赋值
		    ps.executeUpdate();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		    System.out.println("插入完成！");
			ps.close();
			conn.close();	
			System.out.println("插入完成！");
		}
		catch(SQLException e){	
		}	
	}

public void addbuy(String shop,String person) {
	try{			
		Connection con1 = db.getConcus();
		String sql1="select buy from customers where customers_name=? ";
		ps = (PreparedStatement) con1.prepareStatement(sql1);
//		ps.setString(1, b+"$");
		ps.setString(1, person);
		ResultSet re=ps.executeQuery();
		String s=null;
		while(re.next())
			s=re.getString(1);
		System.out.println(s);
		if(s==null)
			s="";
		String sql2="UPDATE customers SET buy = ? where customers_name=?; ";
		ps = (PreparedStatement) con1.prepareStatement(sql2);
		System.out.println(person);
		ps.setString(1, s+shop+"$");
		ps.setString(2, person);
		ps.execute();
		System.out.println("再次插入完毕");
	}catch(SQLException e1){
		e1.printStackTrace();
	}
	} 
}


