package server;

import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class paiser {
	public static final int PORT = 9001;
	public static Socket[] sk;
    public static volatile int op;
	public static void main(String[] args) throws IOException {
		
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server Started，正在监测9001端口");
		sk=new Socket[3];
		int i=0;
		try {
			while (true) {
				sk[i] = s.accept();
				System.out.println(sk[i].getLocalAddress());
				System.out.println("等待连接");
				if(i==2){
				try 
				{
					System.out.println("拍卖开始");
					new Serveone(sk[0],0);
					new Serveone(sk[1],1);
					new Serveone(sk[2],2);
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

class Serveone extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter pw;
    String n0;
    int id;
	public Serveone(Socket s,int n) throws IOException {
		id=n;
		n0=n+"号";
		socket = s;	
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		start(); // Calls run()
	}

	public void run() {
		try {		
			System.out.println("已创建新连接，ip地址为："+socket.getInetAddress());
			System.out.println("一名客户端连接");
			pw=new PrintWriter(new OutputStreamWriter(paiser.sk[id].getOutputStream()), true);
			pw.println(id);
			//初次连接，发送id给每个人
			while (true) {				
				String str = in.readLine();
            if(str.charAt(0)=='$') {
            	for(int i=0;i<3;i++) {
    				pw = new PrintWriter(new OutputStreamWriter(paiser.sk[i].getOutputStream()), true);   	
    				String s=String.valueOf(str.charAt(1));
    				System.out.println(s);
    				pw.println("$"+s);
    				}
            }
            else {
				for(int i=0;i<3;i++) {
				pw = new PrintWriter(new OutputStreamWriter(paiser.sk[i].getOutputStream()), true);
				pw.println(n0+": "+str);
				}
            }
			}
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
