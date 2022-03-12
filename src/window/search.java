package window;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Listener.itemlis;
import dbconnect.db;

public class search implements ActionListener{
	String like;
	public search() {
		
	}
	public void actionPerformed(ActionEvent e) {
		like=list.jtup.getText();
		// TODO 自动生成的方法存根
		int show=0;
		JFrame jf=new JFrame("搜索");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setSize(600,600);
		jf.setLocationRelativeTo(null); // Center the frame
	    
        JPanel jp=new JPanel();//中间商品陈列
        Container contentPane = jf.getContentPane();
        contentPane.add(jp);
        
		jp.setLayout(new GridLayout(3,3));//设定为3*3表格，即每次显示9个物品
		Connection con1=db.getConshop();
		String [] strpath=new String[30];
		String [] strname=new String[30];
		ResultSet re1;
		//一个存储图片，一个存储次序，有用
		int x=0;
		try {
		PreparedStatement s;
		String sql = "select * from shopitem.shop where shop like ?";
		s = con1.prepareStatement(sql);
		s.setString(1,"%"+like+"%");
		re1=s.executeQuery();
		while(re1.next()) {
			InputStream in=null;OutputStream out=null;
			System.out.println("这3");
			strname[x]=re1.getString(2);//存储文件的名称
			
			java.sql.Blob picture = re1.getBlob("picture");
			 in = picture.getBinaryStream();
			 out = new FileOutputStream("D:/PS修图/搜索/out"+x+".PNG");//字节流转化的目标路径
			 
			strpath[x]="D:/PS修图/搜索/out"+x+".PNG";//存储文件保存的路径
			System.out.println(strpath[x]);
			byte[] buffer = new byte[1024];
			int i = 0;
			while((i = in.read(buffer)) != -1){
				out.write(buffer, 0, i);
			}
			i++;
			in.close();out.close();
			x++;
		}
        
		}
		catch(SQLException | IOException e1){	
			e1.printStackTrace();
		}	//使用商品的名称得到标识来标记面板按钮
		
		while(strname[show]!=null&&show<9) {
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
		System.out.println(show+" jjj");
		jf.add(jp);	
		jf.setVisible(true);
	}
}
