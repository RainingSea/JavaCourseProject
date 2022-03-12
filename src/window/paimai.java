package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class paimai {
    static JLabel jl2;
    static JLabel jl3;
    static int price,id;
    static JPanel jp2;
    static JTextArea jt;
    static Box vb2;
	public static void main(String args[]) throws IOException{
		// TODO 自动生成的方法存根
		JFrame frame=new JFrame();
	    frame.setTitle("Next Innovation");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(700, 600);
	    frame.setLocationRelativeTo(null);
		
	    frame.setLayout(new BorderLayout());
		Box vb=Box.createVerticalBox(); 
		Font fg=new Font("微软雅黑",Font.BOLD,24);//设置大字体
		
		JButton jnn=new JButton("   商品一   ");
		jnn.setFont(fg);
		JButton jnnn=new JButton("   商品二   ");
		jnnn.setFont(fg);
		vb.add(jnn);vb.add(jnnn);
		//vb负责显示所有进行拍卖的物品
		
		Border border = BorderFactory.createBevelBorder(1);
		border = BorderFactory.createLineBorder(Color.black, 7, false);
        Border border5 = BorderFactory.createTitledBorder(border);
        vb.setBorder(border5);
		frame.add(vb,BorderLayout.WEST);//将拍卖商品列表仍在左侧
			//box点击从数据读取拍卖物品
		JPanel jp=new JPanel();	
			border = BorderFactory.createLineBorder(Color.black, 7, false);
	        Border border6 = BorderFactory.createTitledBorder(border);
			jp.setBorder(border6);
			frame.add(jp,BorderLayout.CENTER);
	 // 中心的panel显示拍卖的面板		
			jp.setLayout(new GridLayout(1,2));
			JPanel jp1=new JPanel();
			jp1.setLayout(new GridLayout(2,1));
			border = BorderFactory.createLineBorder(Color.black, 2, false);
	        Border border7 = BorderFactory.createTitledBorder(border);
			jp1.setBorder(border7);
			JPanel jp11=new JPanel(){
	            public void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                ImageIcon ii = new ImageIcon("D:/PS修图/ser0.PNG");//演示的时候把这个改回strpath，记得秒删              
	                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
	            }
	        };
	        JPanel jp111=new JPanel();
	        jp111.setLayout(new GridLayout(2,1));
			JLabel jl11=new JLabel("叙述");
			JButton jb11=new JButton("增加10元");
			jb11.setFont(fg);
			jp111.add(jl11);jp111.add(jb11);
			jp1.add(jp11);jp1.add(jp111);
			
			jp.add(jp1);//左侧的面板加载图片，拍卖者概述，以及按钮加价
			
		    jp2=new JPanel();
			jp2.setLayout(new BorderLayout());
			JPanel jp21=new JPanel();
			jp21.setLayout(new GridLayout(2,1));
			id=1;
			price=100;
			String s="当前价格为: "+price+"元";
			String s2="当前持有者为"+id+"号";
			jl2=new JLabel(s);
			jl2.setForeground(Color.red);
			jl3=new JLabel(s2);
			jl3.setForeground(Color.red);
			jl2.setFont(fg);
			jl3.setFont(fg);
			jp21.add(jl2);
			jp21.add(jl3);
			jp2.add(jp21,BorderLayout.NORTH);
			//上方记载所有拍卖商品的价格，和最强买家
			
			vb2=Box.createVerticalBox(); 
			jp2.add(vb2,BorderLayout.CENTER);
			
			 jt=new JTextArea(5,1);
			JButton jbdown=new JButton("发     送");

			jbdown.setFont(fg);
		    JPanel jpdown=new JPanel();
		    jpdown.setLayout(new GridLayout(2,1));
		    jpdown.add(jt);jpdown.add(jbdown);
			jp2.add(jpdown,BorderLayout.SOUTH);
			//中央为拍卖者发布信息，参与者不能发布，只能加价
			jp.add(jp2);
			
			frame.setVisible(true);
			
			Socket socket = null;
			try {
				socket = new Socket("localhost",9001);
				ActionListener infor=new infor(socket);
				jbdown.addActionListener(infor);
			} catch (IOException e2) {
				// TODO 自动生成的 catch 块
				e2.printStackTrace();
			}
			   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			      try {
			          String msg;//服务器发过来的信息
			          while ((msg = bufferedReader.readLine()) != null) {
			            	if(msg.charAt(0)=='$') {
			            		char idi=msg.charAt(1);
			            		price+=10;
			            		String ss="当前价格为: "+price+"元";
			        			String ss2="当前持有者为"+idi+"号";
			        			jl2.setText(ss);
			        			jl3.setText(ss2);
			        			jp21.validate();
			            	}
			            	//如果服务器返回数字，说明有人加价，自己这里改变一下
			            	else {
			        	JLabel jl=new JLabel();
			      	    Font fn=new Font("宋体",Font.BOLD,16);
			      	    jl.setText(msg);
			      	    jl.setFont(fn);
			      	    jl.setForeground(Color.blue);
			      	    paimai.vb2.add(jl);
			      	    paimai.vb2.validate();
			      	    //否则是有人发言，改变发言板
			          }
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
			      
	}
}

class infor implements ActionListener{
	int id;Socket so;private PrintWriter printWriter;
public infor(Socket s) {	
	so=s;
	try {
		printWriter = new PrintWriter(new OutputStreamWriter(so.getOutputStream()), true);
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}
	public void actionPerformed(ActionEvent e) {
        
	    String msg=paimai.jt.getText();
	    printWriter.println(msg);
	    JLabel jl=new JLabel();
	    Font fn=new Font("宋体",Font.BOLD,16);
	    jl.setText("发布者: "+msg);
	    jl.setFont(fn);
	    jl.setForeground(Color.blue); 
	}	
}