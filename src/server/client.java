package server;

import java.net.*;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class client {
	 static JTextArea jta;static Box vb;static JFrame frame;
  public static void main(String[] args) 
      throws IOException {
//	    frame=new JFrame();
//	    frame.setTitle("聊天");
//	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
//	    frame.setSize(300, 600);
//	    frame.setLayout(null);
//	    frame.setLocationRelativeTo(null);   
//	
//	    //设置整个窗体布局
//		vb = Box.createVerticalBox();
//	    JScrollPane sc=new JScrollPane(vb);
//	    sc.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
//      //设置滚动条
//	    Border border = BorderFactory.createBevelBorder(1);
//		border = BorderFactory.createLineBorder(Color.black, 5, false);
//      Border border5 = BorderFactory.createTitledBorder(border);
//      vb.setBorder(border5);
//      sc.setBounds(0, 0, 300, 350);
//      	
//	    jta=new JTextArea();
//		JScrollPane sc1=new JScrollPane(jta);
//		sc1.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
//		sc1.setBounds(0, 350, 300, 150);
//		
//		
//		  JButton jb=new JButton("发    送");
//		  jb.setFont(new java.awt.Font("Dialog", 1, 15));
//		    jb.addActionListener(new ActionListener(){
//		    	public void actionPerformed(ActionEvent e) {
//		    		String comment=jta.getText();
//					JLabel jl=new JLabel();
//					jl.setFont(new java.awt.Font("Dialog", 1, 15));
//					jl.setForeground(Color.blue);
//					jl.setText("我: "+comment);
//					jta.setText("");
//					vb.add(jl);
//					frame.validate();			
//					//此处还要执行一个方法，来把评论放在数据库中
//		    	}
//		    });
//		    jb.setBackground(Color.white);
//		    jb.setBounds(0,500,300,70);   
//		    frame.add(sc);
//		    frame.add(sc1);
//		    frame.add(jb);	
//		frame.setVisible(true);
		//建出初始窗口
      InetAddress addr = InetAddress.getByName(null);
      System.out.println("addr = " + addr);
      Socket socket = new Socket(addr, noline.PORT);
      new Thread(new MyClientWriter1(socket)).start();
      
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      try {
          String msg;//服务器发过来的信息
          while ((msg = bufferedReader.readLine()) != null) {
              System.out.println(msg);
//              String str=msg;
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

class MyClientWriter1 implements Runnable{
    private Socket socket = null;
    private PrintWriter printWriter;
    private Scanner scanner;
    public MyClientWriter1(Socket socket) throws IOException {
        this.socket = socket;
//        scanner = new Scanner(client.jta.getText());
        scanner=new Scanner(System.in);
        printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        String msg;//要发送的信息
        try {        	 	
            while ((msg=scanner.next()) != null) {
                System.out.println("isClosed="+socket.isClosed());
//				JLabel jl=new JLabel();
//				jl.setFont(new java.awt.Font("Dialog", 1, 15));
//				jl.setForeground(Color.blue);
//				jl.setText("我: "+msg);
//				client.jta.setText("");
//				client.vb.add(jl);
//				client.frame.validate();	
                if(socket.isClosed()) {
                    break;
                } else {
                    if(msg.equals("88")) {
                        break;
                    }
                }
                printWriter.println(msg);
            }
        } catch (Exception e) {
        	e.printStackTrace();
           System.out.println("异常关闭客户端（可能是直接关闭退出窗口）");
        }
        System.out.println("写线程准备死亡");
    }
}
