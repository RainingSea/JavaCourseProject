package Listener;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dbconnect.db;

public class searlis implements ActionListener {
	String like;
	public searlis() {
		like=like;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		int show=0;
		JFrame jf=new JFrame("搜索");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setSize(400,600);
		jf.setLocationRelativeTo(null); // Center the frame
	    
        JPanel jp=new JPanel();//中间商品陈列
        Container contentPane = jf.getContentPane();
        contentPane.add(jp);
        
		jp.setLayout(new GridLayout(3,3));//设定为3*3表格，即每次显示9个物品
		
		//开始一个一个物品来添加，使用for循环来进行判断是否有数据可以显示
		Connection con1=db.getConshop();
		String [] strpath=new String[100];
		String [] strname=new String[100];
		ResultSet re1;
		//一个存储图片，一个存储次序，有用
		int i=0;
		try {
		Statement s = con1.createStatement();
		String sql = "select * from all_shop where iname like  ";
		re1=s.executeQuery(sql);
		while(re1.next()) {
			strpath[i]=re1.getString(3);
			strname[i]=re1.getString(2);
			i++;
		}
		System.out.println(strpath[4]);
		//得到商品的图片路径并存储在本地
//		int j=0;
//		String sql2 = "SELECT iname FROM all_shop ";
//		re1=s.executeQuery(sql2);
//		while(re1.next()) {
//			
//			j++;
//		}
  	}
		catch(SQLException e1){	
		}	
		//使用商品的名称得到标识来标记面板按钮
		
		while(strpath[show]!=null&&show<9) {
		System.out.print(show+" ");
		System.out.println(strpath[show]);	
		
		String na=strname[show];	
		String pa=strpath[show];
		show++;
		
		JButton b1=new JButton(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon(pa);
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };
        
        ActionListener lisitem=new itemlis(na,pa);
	    b1.addActionListener(lisitem);
	    jp.add(b1);
		  }
		//创建完毕
		
		System.out.println(show+" jjj");
		jf.add(jp);
		
		jf.setVisible(true);
	    
//	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=3;gbs.gridheight=3;
//		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
//		gbs.gridx=0;gbs.gridy=1;
//		gbl.setConstraints(jp, gbs);
//		jpp.add(jp);
		//中央主要面板，显示商品用
		
		//上一页按钮
//	    JButton bu=new JButton("上一页");
//	    bu.setBackground(Color.white);
//	    bu.addActionListener(new ActionListener() {
//	    	public void actionPerformed(ActionEvent e) {
//	    		if(show-9>0) {
//	            jp.removeAll();
//	            jp.setLayout(new GridLayout(3,1));
//	            System.out.println("已经");
//	            System.out.println(show);
//	            ActionListener lisitem=new itemlis("测试商品","D:/PS修图/日本街道2P.PNG");
//	            
//	            for(int shang=0;shang<9;shang++){
//	            	JButton jbnew=new JButton(){
//	                public void paintComponent(Graphics g) {
//	                    super.paintComponent(g);
//	                    ImageIcon ii = new ImageIcon("D:/PS修图/日本街道2P.PNG");
//	                    g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
//	                }
//	            };
//	            
//	            jbnew.addActionListener(lisitem);
//	            //新建jbutton结束
//	            jp.add(jbnew);    
//	            show--;
//	            }
//	            System.out.println("结束");
//	            jp.validate();
//	            gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=3;gbs.gridheight=3;
//	    		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
//	    		gbs.gridx=0;gbs.gridy=0;
//	    		gbl.setConstraints(jp, gbs);
//	    		jpp.add(jp);
//	           }
//	    		else {}
//	    	}
//	            });
	    
	    //下一页按钮
//	    JButton bd=new JButton("下一页");
//	    bd.setBackground(Color.white);
//	    bd.addActionListener(new ActionListener() {
//           public void actionPerformed(ActionEvent e) {
//            jp.removeAll();
//            jp.setLayout(new GridLayout(3,1));
//            System.out.println("已经");
//            System.out.println(show);
//            ActionListener lisitem=new itemlis("测试商品","D:/PS修图/日本街道2P.PNG");
//            
//            while(strpath[show]!=null&&show<=13) {
//            	JButton jbnew=new JButton(){
//                public void paintComponent(Graphics g) {
//                    super.paintComponent(g);
//                    ImageIcon ii = new ImageIcon("D:/PS修图/日本街道2P.PNG");
//                    g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
//                }
//            };
//            
//            jbnew.addActionListener(lisitem);
//            //新建jbutton结束
//            jp.add(jbnew);    
//            show++;
//            }
//            System.out.println("结束");
//            jp.validate();
//            gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=3;gbs.gridheight=3;
//    		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
//    		gbs.gridx=0;gbs.gridy=0;
//    		gbl.setConstraints(jp, gbs);
//    		jpp.add(jp);
//           }
//            });	
	}
}
