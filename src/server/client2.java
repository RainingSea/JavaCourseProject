package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class client2 {
	 static JTextArea jta;static Box vb;static JFrame frame;
 public static void main(String[] args) 
     throws IOException {

     InetAddress addr = InetAddress.getByName(null);
     System.out.println("addr = " + addr);
     Socket socket = new Socket(addr, noline.PORT);
     new Thread(new MyClientWriter1(socket)).start();
     
     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     try {
         String msg;//服务器发过来的信息
         while ((msg = bufferedReader.readLine()) != null) {
             System.out.println(msg);
//             String str=msg;
//				JLabel jl=new JLabel();
//				jl.setFont(new java.awt.Font("Dialog", 1, 15));
//				jl.setForeground(Color.red);
//				jl.setText("对方: "+str);
//				vb.add(jl);
//				frame.validate();      
         }
     } catch (IOException e) {
         System.out.println("警告：断开连接！");
         try {
             if (!socket.isClosed()) {
                 socket.close();
             }
         } catch (IOException e1) {
             System.out.println("读取线程：关闭socket出现错误");
         }
     }
     System.exit(1);
 }
}



