package window;

import java.awt.BorderLayout;



import java.awt.Color;
import java.awt.Dimension;
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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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

import Listener.recevier;
import Listener.talkleft;
import Listener.talklis;
import Listener.uplis;
import dbconnect.db;

public class user extends JFrame {
	static JTextField jtdelete;
	public user(String name,String born) throws UnknownHostException, IOException {	 
     JFrame jf=new JFrame();
     jf.setSize(700,700);
     jf.setLayout(new GridLayout(2,1));//大体布局
     jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     
     JPanel jpd=new JPanel(new GridLayout(1,2));
     Border border = BorderFactory.createBevelBorder(1);
	 border = BorderFactory.createLineBorder(Color.BLACK, 7, false);
     Border border5 = BorderFactory.createTitledBorder(border);
     jpd.setBorder(border5);
     
     JPanel jp1=new JPanel();
     jp1.setLayout(new GridLayout(1,1));
     String list=getsell(name);
     //得到登陆者的发售信息
     Box vb = Box.createVerticalBox();  

	 int i1=0;
	 String str=""; 
	 JButton jbx=new JButton("                已发售               ");
	 Font fu=new Font("微软雅黑",Font.BOLD,24);
	 jbx.setFont(fu);
	 jbx.setBackground(Color.white);
	 jbx.setForeground(Color.red);
	 vb.add(jbx);
	 if(list!=null)
	 {
	 while(i1<list.length()) {
		 
		
		 if(list.charAt(i1)=='$') {		 
			 JButton jb=new JButton();
			 jb.setFont(fu);
			 jb.setText("         "+str+"                   ");
//			 jb.setBorder(BorderFactory.createRaisedBevelBorder());
//			 jb.setMaximumSize(vb.getMaximumSize());
//			 jb.setPreferredSize(vb.getMaximumSize());
			 vb.add(jb);
			 str="";
		 }
	     else {
			str+=list.charAt(i1); 
		 }
		 i1++;
	 }
	 }
	 else { 
	 }
		 
	 JScrollPane sc=new JScrollPane(vb);
     sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	 sc.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
	 jp1.add(sc);
	 
//     JButton jb1=new JButton("");
//     JButton jb2=new JButton("");
//     JButton jb3=new JButton("");
//     JButton jb4=new JButton("");
//     jp1.add(jb1); jp1.add(jb2); jp1.add(jb3); jp1.add(jb4);
     //已经购买的
     JPanel jp2=new JPanel();
     jp2.setLayout(new GridLayout(1,1));
     String list1=getbuy(name);
     //得到登陆者的发售信息
     Box vb1= Box.createVerticalBox();  
     JButton jbx1=new JButton("              购买意向                ");
     jbx1.setBackground(Color.white);
	 jbx1.setFont(fu);
	 jbx1.setForeground(Color.red);
	 vb1.add(jbx1);
	 int i2=0;
	 String str1="";
	 if(list1!=null)
	 {
	 while(i2<list1.length()) {
		 if(list1.charAt(i2)=='$') {		 
			 JButton jb=new JButton();
			 ActionListener talk=new talkleft(0);
			 jb.addActionListener(talk);			 
			 jb.setFont(fu);
			 jb.setText("         "+str1+"                   ");
//			 jb.setBorder(BorderFactory.createRaisedBevelBorder());
//			 jb.setMaximumSize(vb.getMaximumSize());
//			 jb.setPreferredSize(vb.getMaximumSize());
			 vb1.add(jb);
			 str1="";
		 }
	     else {
			str1+=list1.charAt(i2); 
		 }
		 i2++;
	 }
	 }
	 else { 
	 }
		 
	 JScrollPane sc1=new JScrollPane(vb1);
     sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	 sc1.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
	 jp2.add(sc1);
     
     //已经发售的
     
     
     
     jpd.add(jp1);jpd.add(jp2);
   
     JPanel jpu=new JPanel();//上方大布局
    
     jpu.setLayout(new GridLayout(2,1));
    
    JPanel jpppp=new JPanel(){
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon ii = new ImageIcon("D:/PS修图/原图/背景.PNG");//演示的时候把这个改回strpath，记得秒删
          
            g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
        }
    };
     border = BorderFactory.createLineBorder(Color.BLACK, 2, false);
     Border border6 = BorderFactory.createTitledBorder(border);
    jpppp.setBorder(border6);
     
     
     
     JPanel jpu1=new JPanel();
     jpu1.setLayout(new BorderLayout());//上面的中间
     
     JLabel jl1=new JLabel("头像");
     
     jl1.setBorder(border6);
     Dimension d = new Dimension(200,200);
     jl1.setPreferredSize(d);
     //设置用户头像
     
     JPanel jpu2=new JPanel();
     GridBagLayout gbl=new GridBagLayout();
     jpu2.setLayout(gbl);
     GridBagConstraints gbs = new GridBagConstraints();
     JLabel jl2=new JLabel("用户名称");
     gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=2;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=0.25;gbs.weighty=1;
		gbs.gridx=0;gbs.gridy=0;
		gbl.setConstraints(jl2, gbs);
     jpu2.add(jl2);
     
     JLabel jl3=new JLabel("注册时间");
     gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=2;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=0.25;gbs.weighty=1;
		gbs.gridx=0;gbs.gridy=1;
		gbl.setConstraints(jl3, gbs);
     jpu2.add(jl3);//
     
     JTextField jt1=new JTextField();
     jt1.setText(name);
     gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=6;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=2;gbs.gridy=0;
		gbl.setConstraints(jt1, gbs);
     jpu2.add(jt1);
     
     JTextField jt2=new JTextField(born);
     gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=6;gbs.gridheight=1;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=2;gbs.gridy=1;
		gbl.setConstraints(jt2, gbs);     
		 jpu2.add(jt2);
		 
     JPanel jpu3=new JPanel();
     jpu3.setLayout(new GridLayout(1,3));
     JButton jbu1=new JButton("发布");
     ActionListener upload=new uplis(name);
     jbu1.addActionListener(upload);
     
     JButton jbu2=new JButton("取消发布");
     jbu2.addActionListener(new ActionListener() {
    	public void  actionPerformed(ActionEvent e){
    		 JFrame jf=new JFrame("删除商品");
    		 jf.setTitle("Next Innovation");
    		    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		    jf.setSize(300, 200);
    		    jf.setLocationRelativeTo(null);
    		    Border border = BorderFactory.createBevelBorder(1);
    			border = BorderFactory.createLineBorder(Color.BLACK, 3, false);
    	        Border border5 = BorderFactory.createTitledBorder(border);
    		 jtdelete=new JTextField(20);
    		 jtdelete.setBorder(border5);
    		 JButton Bb=new JButton("请确认删除");
    		 Font f1=new Font("微软雅黑",Font.BOLD,12);//设置字体格式
    		 Bb.setFont(f1);
    		 delete de=new delete(name);
    		 Bb.addActionListener(de); 		
    		 jf.setLayout(new GridLayout(2,1));
    		 jf.add(jtdelete);jf.add(Bb);
    		 jf.setVisible(true);
    	 }
     });     
     JButton jbu29=new JButton("购买成功");
     jbu2.addActionListener(new ActionListener() {
    	public void  actionPerformed(ActionEvent e){
    		 JFrame jf=new JFrame("删除商品");
    		 jf.setTitle("Next Innovation");
    		    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		    jf.setSize(300, 200);
    		    jf.setLocationRelativeTo(null);
    		    Border border = BorderFactory.createBevelBorder(1);
    			border = BorderFactory.createLineBorder(Color.BLACK, 3, false);
    	        Border border5 = BorderFactory.createTitledBorder(border);
    		 jtdelete=new JTextField(20);
    		 jtdelete.setBorder(border5);
    		 JButton Bb=new JButton("请确认删除");
    		 Font f1=new Font("微软雅黑",Font.BOLD,12);//设置字体格式
    		 Bb.setFont(f1);
    		 delete de2=new delete(name);
    		 Bb.addActionListener(de2); 		
    		 jf.setLayout(new GridLayout(2,1));
    		 jf.add(jtdelete);jf.add(Bb);
    		 jf.setVisible(true);
    	 }
     });     
     JPanel jp29=new JPanel();
     jp29.setLayout(new GridLayout(2,1));
     jp29.add(jbu2);jp29.add(jbu29);
     
     JButton jbu3=new JButton("我的留言");
     ActionListener recevier=new recevier();
     jbu3.addActionListener(recevier);
     
     JButton jbu4=new JButton("在线聊天");
     ActionListener talklis=new talklis();
     jbu4.addActionListener(talklis);
     
     JPanel jp3=new JPanel();
     jp3.setLayout(new GridLayout(2,1));
     jp3.add(jbu3);jp3.add(jbu4);//整合两个聊天按钮
     jpu3.add(jbu1);jpu3.add(jp29);jpu3.add(jp3);
     
     jpu1.add(jl1,BorderLayout.WEST);jpu1.add(jpu2,BorderLayout.CENTER);jpu1.add(jpu3,BorderLayout.EAST);
     jpu.add(jpppp);//上面的留白
     jpu.add(jpu1);
     
     jf.add(jpu);
     jf.add(jpd);
     
     jf.setVisible(true);
     System.out.println(jp1.getHeight());
     System.out.println(jp1.getWidth());
	}

	private String getsell(String name) {
		java.sql.Connection con=db.getConcus();
		String result="";
		String sql="SELECT sell FROM customers where customers_name=?;";
		try{				
			System.out.println("查询发售商品");
			PreparedStatement ps =con.prepareStatement(sql);			
			ps.setString(1,name); //给占位符赋值
		    ResultSet re=ps.executeQuery();  
		
			  while(re.next()) {
				result=re.getString(1);	
			    }
		   
			ps.close();
			con.close();	
			System.out.println(result);
			System.out.println("发售商品完成！");
		}
		catch(SQLException e){	
			e.printStackTrace();
		}	
		return result;
	}


private String getbuy(String name) {
	java.sql.Connection con=db.getConcus();
	String result="";
	String sql="SELECT buy FROM customers where customers_name=?;";
	try{				
		System.out.println("查询心仪商品");
		PreparedStatement ps =con.prepareStatement(sql);			
		ps.setString(1,name); //给占位符赋值
	    ResultSet re=ps.executeQuery();  
	
		  while(re.next()) {
			result=re.getString(1);	
		    }
	   
		ps.close();
		con.close();	
		System.out.println(result);
		System.out.println("商品显示完成！");
	}
	catch(SQLException e){	
		e.printStackTrace();
	}	
	return result;
}
}

class delete implements ActionListener{
    String shop,owner;
    private PrintWriter printWriter;
    public delete(String p) {
    	owner=p;
    }
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		System.out.println("delete监听器已调用");
		shop=user.jtdelete.getText();
		InetAddress addr;
		try {
			addr = InetAddress.getByName(null);
			  System.out.println("addr = " + addr);
			  Socket socket = new Socket(addr,8999);
			  DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
			  String msg=shop;
			  System.out.println(msg);
			  out.writeUTF("d");
			  out.writeUTF(msg);
			  out.writeUTF(owner);
			  System.out.println("delete监听器ok");
			  socket.close();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}	
	}

	class delete2 implements ActionListener{
	    String shop,owner;
	    private PrintWriter printWriter;
	    public delete2(String p) {
	    	owner=p;
	    }
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			System.out.println("delete监听器已调用");
			shop=user.jtdelete.getText();
			InetAddress addr;
			try {
				addr = InetAddress.getByName(null);
				  System.out.println("addr = " + addr);
				  Socket socket = new Socket(addr,8999);
				  DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	        
				  String msg=shop;
				  System.out.println(msg);
				  out.writeUTF("f");
				  out.writeUTF(msg);
				  out.writeUTF(owner);
				  System.out.println("delete监听器ok");
				  socket.close();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		   
			
		}
	}
}
