package Listener;

import java.net.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import server.MultiJabberServer;
import server.noline;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;


public class talklis implements ActionListener {
	 static JTextArea jta;static Box vb;static JFrame frame;
	 static JTextArea ta;static JTextField tf;
	 private static PrintWriter printWriter;
	 
   public void actionPerformed(ActionEvent e) {
   	
	    frame=new JFrame();
	    frame.setTitle("聊天");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
	    frame.setSize(300, 600);
	    frame.setLayout(null);
	    frame.setLocationRelativeTo(null);   
	
	    //设置整个窗体布局
		vb = Box.createVerticalBox();
	    JScrollPane sc=new JScrollPane(vb);
	    sc.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
     //设置滚动条
	    Border border = BorderFactory.createBevelBorder(1);
		border = BorderFactory.createLineBorder(Color.black, 5, false);
     Border border5 = BorderFactory.createTitledBorder(border);
     vb.setBorder(border5);
     sc.setBounds(0, 0, 300, 350);
	    
		 tf=new JTextField();
		tf.setBounds(0, 350, 300, 150);  
	
		//建出初始窗口
		
     InetAddress addr;

	  try {
		    Socket socket;
	        addr = InetAddress.getByName(null);
	        socket = new Socket(addr, 8080);			
            new Thread(new MyClientReader(socket)).start();
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
	  }
     catch (IOException e1) {
 		// TODO 自动生成的 catch 块
 		e1.printStackTrace();
 	}

     tf.addFocusListener(new JTextFieldListener(tf,"请在此输入内容"));
     JButton jb=new JButton("发送");
     jb.setBounds(0,500,300,70);  
     jb.setBackground(Color.white);
     jb.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             if(tf.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(talklis.frame, "请输入内容！");
             }else {
           	    printWriter.println(tf.getText());   
           	    JLabel jl=new JLabel(tf.getText());
					jl.setFont(new java.awt.Font("Dialog", 1, 15));
					jl.setForeground(Color.blue);
					vb.add(jl);
					frame.validate();
                   tf.setText("");
             }           
         }
     });
	    frame.add(sc);
	    frame.add(tf);
	    frame.add(jb);	
	   frame.setVisible(true);

}

}
           
 class MyClientReader implements Runnable{
	    private Socket socket = null;
	    private BufferedReader bufferedReader;
	    public MyClientReader(Socket socket) throws IOException {
	        this.socket = socket;
	        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    }
	    @Override
	    public void run() {
      try {
       String msg;//服务器发过来的信息
       while ((msg = bufferedReader.readLine())!= null) {
           System.out.println(msg);
           String str=msg;
			JLabel jl=new JLabel();
			jl.setFont(new java.awt.Font("Dialog", 1, 15));
			jl.setForeground(Color.red);
			jl.setText("对方: "+str);
			System.out.println(str+"ssss");
			talklis.vb.add(jl);
			talklis.frame.validate();      
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
 class JTextFieldListener implements FocusListener{
     
     private String hintText;          //提示文字
     private JTextField textField;
     
     public JTextFieldListener(JTextField textField,String hintText) {
         this.textField=textField;
         this.hintText=hintText;
         textField.setText(hintText);   //默认直接显示
         textField.setForeground(Color.GRAY);
     }

     @Override
     public void focusGained(FocusEvent e) {
         
         //获取焦点时，清空提示内容
         String temp=textField.getText();
         if(temp.equals(hintText)){
             textField.setText("");
             textField.setForeground(Color.BLACK);
         }
         
     }

     @Override
     public void focusLost(FocusEvent e) {
         
         //失去焦点时，没有输入内容，显示提示内容
         String temp=textField.getText();
         if(temp.equals("")) {
             textField.setForeground(Color.GRAY);
             textField.setText(hintText);
         }
         
     }
     
 }
