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
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Listener.talkleft;
import Listener.talklis;

public class itemwin extends JFrame {
	static Box vb;static JPanel jp21;
	static JTextField jt211;
	
	public itemwin(String name,String path) {
		
		JFrame frame=new JFrame();
	    frame.setTitle(name);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setSize(600, 600);
	    frame.setLayout(new GridLayout(1,2));
	    
	    GridBagLayout gbl = new GridBagLayout();
	    JPanel jpframe=new JPanel();
		jpframe.setLayout(gbl);	
		GridBagConstraints gbs = new GridBagConstraints();     
	    //设置整个窗体布局
		System.out.println(path);
	    JPanel jp1=new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon(path);
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };
        gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=0;gbs.gridy=0;
		gbl.setConstraints(jp1, gbs);
		jpframe.add(jp1);
		//第一块为商品的预览图
		
        JLabel jl=new JLabel();
        jl.setFont(new java.awt.Font("Dialog", 1, 15)); //设置字体
        jl.setForeground(Color.red);
        jl.setText("<html><p>有很好的效果</p></p>可以重复使用的！</p></html>");
        Border border = BorderFactory.createBevelBorder(1);
		border = BorderFactory.createLineBorder(Color.white, 7, false);
        Border border5 = BorderFactory.createTitledBorder(border);
        jl.setBorder(border5);
        
        gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=0.1;
		gbs.gridx=0;gbs.gridy=1;
		gbl.setConstraints(jl, gbs);
		jpframe.add(jl);
	    //第二块为发布者自己加的说明
		
	    
	    jp21=new JPanel();
	    jp21.setLayout(new BorderLayout());
	    
	    //第一个面板显示评论
	    vb = Box.createVerticalBox();
	    JLabel jlp=new JLabel("欢迎前来评论");
	    Font f=new Font("微软雅黑",Font.BOLD,18);//设置字体格式
	    jlp.setFont(f); 
	    
	    JScrollPane sc=new JScrollPane(vb);
        vb.add(jlp);
	    sc.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        //设置滚动条
	    jp21.add(sc,BorderLayout.CENTER);
        showcomment(name);//从数据库中搜索评论
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=3;gbs.gridheight=3;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=0;gbs.gridy=2;
		gbl.setConstraints(jp21, gbs);
		jpframe.add(jp21);
	    
	    JPanel jpfr=new JPanel();
	    jpfr.setLayout(new GridLayout(2,1));
	    jt211=new JTextField(20);
	    border = BorderFactory.createLineBorder(Color.red, 4, false);
        Border border6 = BorderFactory.createTitledBorder(border);
        jt211.setBorder(border6);
	    
	    //分别显示所有的评论，用一个竖状条查看更多
	    
	    jpfr.add(jt211);
	     
	    JButton jb21=new JButton("发  表  看  法");
	     ActionListener com=new comment(name);
	    jb21.addActionListener(com);
	    jb21.setBackground(Color.white);
	    
	    JButton jb22=new JButton("联  系  商  家");
	    jb22.setBackground(Color.white);
	    ActionListener talklis=new talklis();
	    jb22.addActionListener(talklis);
	    
	    JButton jb23=new JButton("标  记  为  喜  欢");
	    jb23.setBackground(Color.white);
	    String you=list.name;
	    mask mask=new mask(name,you);
	    jb23.addActionListener(mask);
	    
	    JButton jb24=new JButton("留  言  商  家");
	    jb24.setBackground(Color.white);
	    ActionListener left=new talkleft(0);
	    jb24.addActionListener(left);
	    
	    JPanel jpddd=new JPanel();
	    jpddd.setLayout(new GridLayout(4,1));
	    
	    jpddd.add(jb21);jpddd.add(jb22);jpddd.add(jb23);jpddd.add(jb24);
	    
	    jpfr.add(jpddd);
	    //发表评论的按钮
	    
	    frame.add(jpframe);frame.add(jpfr);
	    //第三个为进行的评论	
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
  }
	public void showcomment(String na){
		System.out.println(na+"  展示评论");
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
		   String sql="SELECT comment FROM comment where shop=?;";
			try{				
				System.out.println("查询评论完成");
				PreparedStatement ps =conn.prepareStatement(sql);			
				ps.setString(1,na); //给占位符赋值
   			    ResultSet re=ps.executeQuery();    
   			    //一个个添加评论，遇见$停止
   			 Font f=new Font("微软雅黑",Font.BOLD,18);//设置字体格式 
   			    while(re.next()) {
   			    	System.out.println(re.getString(1));
   			    	String list=re.getString(1);
   			    	int i1=0;
   			    	String str="";
   			    	if(list!=null) {
   				 while(i1<list.length()) {
   					 if(list.charAt(i1)=='$') {
   						 JLabel jl=new JLabel(str);
   						 jl.setFont(f);
   						 vb.add(jl);
   						 str="";
   					 }
   				     else {
   						str+=list.charAt(i1); 
   					 }
   					 i1++;
   				 }				
   					jp21.validate();
   			    }
   			    else {
   			    	
   			    }
				ps.close();
				conn.close();	
				System.out.println("评论显示完成！");
			}
			}
			catch(SQLException e){	
			}	
		}
}
	
class mask implements ActionListener{
String shop;String name;

public mask(String a,String b) {
	shop=a;name=b;
}
public void actionPerformed(ActionEvent e) {
	// TODO 自动生成的方法存根
	try {
		Socket so=new Socket("localhost", 8999);
		DataOutputStream out =
                new DataOutputStream(so.getOutputStream());
		out.writeUTF("m");
		out.writeUTF(shop);
        out.writeUTF(name);
		System.out.println("完成");
		
	} catch (IOException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}
}
}


class comment implements ActionListener{
String com;String name;
public comment(String name) {
	this.name=name;
}
public void actionPerformed(ActionEvent e) {
	String comment=itemwin.jt211.getText();
	JLabel jl=new JLabel();
	Font f=new Font("微软雅黑",Font.BOLD,18);
	jl.setFont(f);
	jl.setText(comment);
    jl.setForeground(Color.blue);
	itemwin.vb.add(jl);
	itemwin.jp21.validate();
    com=comment;
	// TODO 自动生成的方法存根
	try {
		Socket so=new Socket("localhost", 8999);
		DataOutputStream out =
                new DataOutputStream(so.getOutputStream());
		out.writeUTF("c");
		
        out.writeUTF(name);
        out.writeUTF(com);
		System.out.println("完成");
		
	} catch (IOException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}

}
}

