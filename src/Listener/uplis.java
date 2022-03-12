package Listener;

import java.awt.BorderLayout;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dbconnect.ImageUtil;
import dbconnect.db;
import projectmain.shop;
import server.noline;
public class uplis extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	static String na;
    public uplis(String name) {
    	na=name;
    }
    static JTextField jtu1;static JTextField jtu2;
    static JLabel lpicture;
	public void actionPerformed (ActionEvent e){  
		    System.out.println(na);
	        setTitle("发布商品");
	        setLocationRelativeTo(null); // Center the frame
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setSize(400, 400);
	              
	        setLayout(new GridLayout(4,1));  
	      //第一行选择标签
	        JPanel jp1=new JPanel();
	        jp1.setLayout(new GridLayout(2,1));
	        JLabel jlu1=new JLabel();
	        jlu1.setText("<html><p>请从以下标签中选择一个类别</p></p>零食 运动 用券</p></html>");
	        jtu1=new JTextField(10);
	        
	        jp1.add(jlu1);jp1.add(jtu1);
	      //第二行输入商品名字  
	        JPanel jp2=new JPanel();
	        jp2.setLayout(new GridLayout(2,1));
	        JLabel jlu2=new JLabel();
	        jlu2.setText("<html><p>请输入商品名字</p></html>");
	        jtu2=new JTextField(20);
	        
	        jp2.add(jlu2);jp2.add(jtu2);
	        
	      //第三行打印文件路径     
	        JPanel jp3=new JPanel();
	        jp3.setLayout(new GridLayout(2,1));
	        lpicture=new JLabel();
	        JButton picture=new JButton("选择图片");
	        ActionListener filechoo=new filechoo();
	        picture.addActionListener(filechoo);
	         	        
	        jp3.add(picture);jp3.add(lpicture);
	       //最后一行按钮进行发布 
	        JButton jb=new JButton("发布商品");
	        
	        add(jp1);
	        add(jp2);
	        add(jp3);
	        add(jb);
	        ActionListener as=new as();
	        jb.addActionListener(as);  
	        setVisible(true);  
	    }    
  class	as implements ActionListener,java.io.Serializable { 
	private static final long serialVersionUID = 1L;
	    String a,b,c;	
	    Image newimg;
	    BufferedImage bimg;
	    byte[] bytes;
	    public void actionPerformed (ActionEvent e){  
	    	a=uplis.jtu1.getText();//类别标签
	    	b=uplis.jtu2.getText();//名字标签
	    	c=uplis.lpicture.getText();//路径标签
	        try
	        {
	        	
	           System.out.println("Connecting to " + "localhost"
	                               + " on port " + 8999);
	           Socket client = new Socket("localhost", 8999);
	   
	           System.out.println("Just connected to "
	                        + client.getRemoteSocketAddress());
	   
	          DataInputStream in=new DataInputStream(client.getInputStream());
	          DataOutputStream out =
                      new DataOutputStream(client.getOutputStream());
        
	          out.writeUTF("i");
	          System.out.println(in.readUTF());
	          System.out.println(in.readUTF());
	   
	        
	           
	           out.writeUTF("Hello from "
	                        + client.getLocalSocketAddress());
	           out.writeUTF("client: hello to server");
	           
               out.writeUTF(a);
               out.writeUTF(b);
               out.writeUTF(na);
	           ImageIcon img1=new ImageIcon("Ashish.jpg");
	           Image img = img1.getImage();
	           Image newimg = img.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH);
	           ImageIcon newIcon = new ImageIcon(newimg);
	   
	           bimg = ImageIO.read(new File(c));
	   
	           ImageIO.write(bimg,"JPG",client.getOutputStream());
	           //不接受.PNG的图片......
	           System.out.println("Image sent!!!!");
	           client.close();
	        }catch(IOException e1)
	        {
	           e1.printStackTrace();
	        }
	    	
	    	
	    	
	    	
	    	
	    	
//	    	System.out.println(c);
//	    	InetAddress addr;
//			try {
//				 addr = InetAddress.getByName(null);
//				 System.out.println("addr = " + addr);
//			     socket = new Socket(addr, 8999);		
//			     
//			     
//			     
//			    
//			     byte[]bytes = null;
//					try {
//						File file = new File(c);
//				        //将文件内容写入字节数组
//				       bytes=Files.readAllBytes(file.toPath());   
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}  
//			     pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
//			     pw.println("i"+a);
//			     pw.println(b);
//			     pw.println(na);
//			     pw.write(bytes.toString());
//			     out = new DataOutputStream(socket.getOutputStream()); 
//			     System.out.println("kkk");
//			     out.write(bytes);  
			     System.out.println("kkk");  
//			     
//    		}catch (UnknownHostException e2) {
//				e2.printStackTrace();
//			} catch (IOException e1) {
//				// TODO 自动生成的 catch 块
//				e1.printStackTrace();
//			}
	    }
  }
//	    	//由刚刚的filechooser获得的路径
//            String sql="insert into shop values(?,?,?,?)";
//
//	            Connection conn=null;
//	            PreparedStatement ps=null;
//	    		try{
//	    			InputStream in=ImageUtil.getImageByte(c);
//	    			conn = db.getConshop();
//	    			ps = (PreparedStatement) conn.prepareStatement(sql);
//	    			ps.setString(1, a); //给占位符赋值
//	    			ps.setString(2, na); 
//	    			ps.setBinaryStream(3, in,in.available());//给占位符赋值
//	    			ps.setString(4, b);    			
//	    			ps.executeUpdate();			//执行
//	    		}catch(SQLException | FileNotFoundException e1){
//	    			e1.printStackTrace();
//	    		} catch (IOException e1) {
//					// TODO 自动生成的 catch 块
//					e1.printStackTrace();
//				}
//	    		finally{
//	    			try {
//	    			ps.close();
//	    			conn.close();	//必须关闭
//	    		}
//	    			catch(SQLException e1){
//		    			e1.printStackTrace();}
//	    		}
//	    		System.out.println("执行插入语句成功");
//	    		}          
//	    }  
   class filechoo extends JFrame implements ActionListener{
		
		private JTextField textField;
		private JPanel panel = new JPanel();
		private JFileChooser fileChooser = new JFileChooser();
		
		public void actionPerformed(ActionEvent e) {
			setTitle("选项卡面板");
			setBounds(400, 400, 400, 400);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			final JLabel label = new JLabel();
			label.setText("文件：");
			panel.add(label);
			
			textField = new JTextField();
			textField.setColumns(20);
			panel.add(textField);
			
			final JButton button = new JButton("上传");
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					int i = fileChooser.showOpenDialog(getContentPane());// 显示文件选择对话框
					
					// 判断用户单击的是否为“打开”按钮
					if (i == JFileChooser.APPROVE_OPTION) {					
						File selectedFile = fileChooser.getSelectedFile();// 获得选中的文件对象
						textField.setText(selectedFile.getName());        // 显示选中文件的名称
						System.out.println(fileChooser.getSelectedFile().getPath());
						lpicture.setText(fileChooser.getSelectedFile().getPath());
					}
				}
			});
			panel.add(button);
			add(panel, BorderLayout.NORTH);
			setVisible(true);
		}
	}
}

