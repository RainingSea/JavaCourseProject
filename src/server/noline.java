package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import dbconnect.ImageUtil;
import dbconnect.db;

public class noline {
	public static final int PORT = 8888;
	static Socket[] sk;
	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("离线连接开始，正在监测8888端口");
		sk=new Socket[3];
		int i=0;
		try {
			while (true) {
				sk[i] = s.accept();
				System.out.println(sk[i].getLocalAddress());			 
				try 
				{
					new ServeOneJabber1(sk[i],i);
					i++;
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		} finally {
			s.close();
		}
	}
}

class ServeOneJabber1 extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
    String n1,n0;
    int no;
    public static volatile String msg="";
	public ServeOneJabber1(Socket s,int n) throws IOException {
	 {
		no=n; 
		socket = s;	
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		start(); // Calls run()
	}
}

	public void run() {
		if(no==0) {
		try {		
			System.out.println("已创建新连接，ip地址为："+socket.getInetAddress());
		    //要发送的离线信息			
			while (true) {				
				String str = in.readLine();				
				if (str.equals("88"))
					break;
				else{msg+=str+"$";}
				System.out.println("Echoing: " + msg);
			}
			System.out.println("离线信息已经发出");
				Connection con=db.getConcus();
				PreparedStatement s = null;
					
						String sql3="insert into message values(?)";

						s=con.prepareStatement(sql3);
						s.setString(1, msg);
						s.execute();
				//负责接收每一句离线信息，并存储起来					
			System.out.println("已经存储！");
			
		} catch (IOException | SQLException e) {
			System.err.println("IO Exception");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}
		}
		
		
	else if(no!=0) {
	    try {
			PrintWriter out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		try {
		System.out.println("已创建新连接，ip地址为："+socket.getInetAddress());
		Connection con=db.getConcus();
		String s11 = null;
		java.sql.Statement s = con.createStatement();
			
				String sql3="select * from message ";

				ResultSet re=s.executeQuery(sql3);	
			while(re.next())
				s11=re.getString(1);
			System.out.println(s11);
			out.println(s11);
			String sql4="delete from message;";
			s.executeUpdate(sql4);
			
			BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = bufferedReader.readLine();	
			PreparedStatement s1 = null;
			
					String sql33="insert into message values(?)";
					s1=con.prepareStatement(sql33);
					s1.setString(1, "好的，那到时见！$");
					s1.execute();
			System.out.println("到这了！");		
		System.out.println("离线信息已经发出");
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} finally {
		try {
			socket.close();
		} catch (IOException e) {
			System.err.println("Socket not closed");
		}
	}
	}
	}
}

