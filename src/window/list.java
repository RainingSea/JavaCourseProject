package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MenuListener;

import com.mysql.cj.protocol.Resultset;

import Listener.Listener;
import Listener.auc;
import Listener.itemlis;
import Listener.searlis;
import dbconnect.db;



public class list {
	static int show =0;
	static JTextField jtup;
	static String name;
	static String [] strpath;
	//作为一个计数器来判定是否停止生成表格
	public list(String name, String born) throws IOException {
		list.name=name;
		System.out.println(show);
        JFrame jf = new JFrame();
        jf.setLayout(new BorderLayout());
        jf.setTitle("Shoplist");
        jf.setLocationRelativeTo(null); // Center the frame
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //设置关闭时仅仅关闭此窗口
        jf.setSize(900, 800);//合理设置大小
	    jf.setLocationRelativeTo(null);
        //左侧商品类别
        JButton jb11=new JButton("零食");
        JButton jb12=new JButton("运动");
        JButton jb13=new JButton("用券");
        JButton jb15=new JButton("拍卖系统");
     //   ActionListener auction=new auc();
    //    jb15.addActionListener(auction);
        JButton jb14=new JButton("个人账户");
        
        ActionListener lis14=new Listener(name,"2001-12-10");
        jb14.addActionListener(lis14);
        JPanel jp11=new JPanel();
        jp11.setLayout(new GridLayout(5,1));
        jp11.add(jb11);jp11.add(jb12);jp11.add(jb13);jp11.add(jb15);jp11.add(jb14);
        jf.add(jp11,BorderLayout.WEST);
        
//        JTextField search=new JTextField();
//        jf.add(search,BorderLayout.NORTH);
	    
		//中央主要布局
		Border border = BorderFactory.createBevelBorder(1);
		border = BorderFactory.createLineBorder(Color.BLACK, 7, false);
        Border border5 = BorderFactory.createTitledBorder(border);
        
        JPanel jpp=new JPanel();
        GridBagLayout gbl = new GridBagLayout();
		jpp.setLayout(gbl);	
		GridBagConstraints gbs = new GridBagConstraints();
		
		jtup=new JTextField(20);
		JButton jbsear=new JButton("搜索");
		ActionListener search=new search();
		jbsear.addActionListener(search);
		JPanel jpsear=new JPanel();//搜索面板，命名来自jb-search
     	jpsear.add(jtup);jpsear.add(jbsear);
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=3;gbs.gridheight=1;
	    gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=0.05;
		gbs.gridx=0;gbs.gridy=0;
		gbl.setConstraints(jpsear, gbs);
		jpp.add(jpsear);
       
        JPanel jp=new JPanel();//中间商品陈列
        Container contentPane = jf.getContentPane();
        contentPane.add(jp);
        jp.setBorder(border5);
		jp.setLayout(new GridLayout(3,3));//设定为3*3表格，即每次显示9个物品
		
		
		//开始一个一个物品来添加，使用for循环来进行判断是否有数据可以显示
		Connection con1=db.getConshop();
		strpath=new String[100];
		strpath=path();
		String [] strname=new String[100];
		ResultSet re1;
		//一个存储图片，一个存储次序，有用
		try {
		Statement s = con1.createStatement();

		//得到商品的图片名字并存储在本地
		int j=0;
		String sql2 = "SELECT shop FROM shop ";
		re1=s.executeQuery(sql2);
		while(re1.next()) {
			strname[j]=re1.getString(1);//记录所有商品的名字，作为数据集存储
			j++;
		}
		}
		catch(SQLException e){	
		}	
		//使用商品的名称得到标识来标记面板按钮
		
		while(strname[show]!=null&&show<9) {
		System.out.print(show+" ");
		System.out.println(strname[show]);	
		
		String na=strname[show];	
		String pa=strpath[show];
		
		show++;
		
		JButton b1=new JButton(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon(pa);//演示的时候把这个改回strpath，记得秒删
              
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };
        
        ActionListener lisitem=new itemlis(na,pa);
	    b1.addActionListener(lisitem);
	    jp.add(b1);
		  }
		//创建完毕
		
		System.out.println(show+"创建完毕");
	    
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=3;gbs.gridheight=3;
		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=0;gbs.gridy=1;
		gbl.setConstraints(jp, gbs);
		jpp.add(jp);
		//中央主要面板，显示商品用
		
		//上一页按钮
	    JButton bu=new JButton("上一页");
	    bu.setBackground(Color.white);
	    bu.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(show-9>0) {
	            jp.removeAll();
	            jp.setLayout(new GridLayout(3,3));
	            show=show-show%9-9;
	            int io=0;
	            while(io<9) {
	            	String p=strpath[show];
	            	JButton jbnew=new JButton(){
	                public void paintComponent(Graphics g) {
	                    super.paintComponent(g);
	                    ImageIcon ii = new ImageIcon(p);
	                    g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
	                }
	            };
	            ActionListener lisitem=new itemlis(strname[show],strpath[show]);
	            jbnew.addActionListener(lisitem);
	            //新建jbutton结束
	            jp.add(jbnew);    
	            show++;
	            io++;
	            }
	            System.out.println("上一页结束");
	            System.out.println(show+"number");
	            jp.validate();
	            gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=3;gbs.gridheight=3;
	    		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
	    		gbs.gridx=0;gbs.gridy=0;
	    		gbl.setConstraints(jp, gbs);
	    		jpp.add(jp);
	           }
	    		else {}
	    	}
	            });
	    
	    //下一页按钮
	    JButton bd=new JButton("下一页");
	    bd.setBackground(Color.white);
	    bd.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   if(strname[show]==null) {
        	   }
        	   else {
            jp.removeAll();
            jp.setLayout(new GridLayout(3,3));
            System.out.println(show);    
            int limit=show+9;
            while(strname[show]!=null&&show<limit) {
            	String path1=strpath[show];
            	JButton jbnew=new JButton(){
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon ii = new ImageIcon(path1);
                    g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
                }
            };
            ActionListener lisitem=new itemlis(strname[show],strpath[show]);
            jbnew.addActionListener(lisitem);
            //新建jbutton结束
            jp.add(jbnew);    
            show++;
            }
            System.out.println("结束");
            if(show<limit) {
            	for(int i=0;i<limit-show;i++) {
            		JButton jb=new JButton();
            	jp.add(jb);
            	}
            }
            System.out.println(show+"number");
            jp.validate();
            gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=3;gbs.gridheight=3;
    		gbs.insets=new Insets(1,1,1,1);gbs.weightx=1;gbs.weighty=1;
    		gbs.gridx=0;gbs.gridy=0;
    		gbl.setConstraints(jp, gbs);
    		jpp.add(jp);
           }
           }
            });
	    //点击下一页继续获得re结果集并生成新的界面
	    
	    JPanel jp1=new JPanel();
	    
	    border = BorderFactory.createLineBorder(Color.GRAY, 1, false);
        Border border6 = BorderFactory.createTitledBorder(border);
        jp1.setBorder(border6);
//        JTextField jt=new JTextField("1/10");
	    jp1.add(bu);
	//    jp1.add(jt);
	    jp1.add(bd);
	   
		
	    jf.add(jp1,BorderLayout.SOUTH);   
	    jf.add(jpp,BorderLayout.CENTER);
	
	    
	    jf.setVisible(true);
	}
	
	public String[] path() throws IOException {
		Connection con=db.getConshop();
		String [] strpath=new String[100];
		//一个存储图片，一个存储次序，有用
		try {
			String sql = "SELECT picture FROM shop ";
        	PreparedStatement preparedstatement=null; 
			preparedstatement = con.prepareStatement(sql);		
			ResultSet resultset=null;
			resultset = preparedstatement.executeQuery();
			int j=0;
			InputStream in = null;OutputStream out = null;
			while(resultset.next()){
				java.sql.Blob picture = resultset.getBlob("picture");
				 in = picture.getBinaryStream();
				 out = new FileOutputStream("D:/PS修图/ser"+j+".PNG");//字节流转化的目标路径
				strpath[j]="D:/PS修图/ser"+j+".PNG";
				byte[] buffer = new byte[1024];
				int i = 0;
				while((i = in.read(buffer)) != -1){
					out.write(buffer, 0, i);
				}
				System.out.println(strpath[j]);
				j++;
				System.out.println(j);
				
			}	
			out.close();
			in.close();
			resultset.close(); preparedstatement.close();con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Hello, World!");
		return strpath;	
	}
	
	public void setIcon(String file, JButton iconButton) {
		ImageIcon icon = new ImageIcon(file);
		Image temp = icon.getImage().getScaledInstance(169,
		203, icon.getImage().SCALE_DEFAULT);
		icon = new ImageIcon(temp);
		iconButton.setIcon(icon);
		} 
	
   public void showlist(int num) {
	   
   }
   }

