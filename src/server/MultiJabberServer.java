package server;

import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MultiJabberServer {
	public static final int PORT = 8080;
	public static Socket[] sk;
    public static volatile int op;
	public static void main(String[] args) throws IOException {
		
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server Started，正在监测8080端口");
		sk=new Socket[4];
		int i=0;
		try {
			while (true) {
				sk[i] = s.accept();
				System.out.println(sk[i].getLocalAddress());
				if(i==1){
				try 
				{
					new ServeOneJabber(sk[i-1],i-1);
					new ServeOneJabber(sk[i],i);
					
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}	
				}
				i++;
			}
		} finally {
			s.close();
		}
	}
	public int getstate() {
		if(op==1)
			return 1;
		else 
			return 0;
	}
}

class ServeOneJabber extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
    String n1,n0;
	public ServeOneJabber(Socket s,int n) throws IOException {
		if(n==0) {
		n0=n+"号";
		socket = s;	
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(MultiJabberServer.sk[1].getOutputStream())), true);
		// If any of the above calls throw an
		// exception, the caller is responsible for
		// closing the socket. Otherwise the thread
		// will close it.
		start(); // Calls run()
	}
		if(n==1) {
			n1=n+"号";
			socket = s;	
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(MultiJabberServer.sk[0].getOutputStream())), true);
			// If any of the above calls throw an
			// exception, the caller is responsible for
			// closing the socket. Otherwise the thread
			// will close it.
			start(); // Calls run()
		}
	}

	public void run() {
		try {		
			System.out.println("已创建新连接，ip地址为："+socket.getInetAddress());
			String r;
			if(n0!=null)
				r=n0;
			else
				r=n1;
			System.out.println("一名客户端连接");
			while (true) {				
				String str = in.readLine();
				if (str.equals("END"))
					break;
				System.out.println("Echoing: " + str);
				out.println(str+"("+r+")");
			}
			System.out.println("服务器:closing...");
		} catch (IOException e) {
			System.err.println("IO Exception");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}
	}
}
