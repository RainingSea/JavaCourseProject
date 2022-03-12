package Listener;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Listener.uplis.as;
import Listener.filechoo;

public class auc implements ActionListener{
    public auc() {
    	 JFrame jf=new JFrame();
 	     jf.setTitle("Next Innovation");
 	     jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	     jf.setSize(500, 400);
 	     jf.setLocationRelativeTo(null);
 	     Font fg=new Font("微软雅黑",Font.BOLD,24);
         JButton jb=new JButton("自 己 进 行 拍 卖");
         jb.setFont(fg);
         ActionListener auclis=new up();
         jb.addActionListener(auclis);//自己进行拍卖
         JButton jb2=new JButton("参 加 一 个 拍 卖");
         ActionListener choose =new auclist();
         jb2.addActionListener(choose);
         jb2.setFont(fg);       
         jf.setLayout(new GridLayout(2,1));
         jf.add(jb);jf.add(jb2);
         jf.setVisible(true);	
    }
	@Override
	public void actionPerformed(ActionEvent e) {
    
	}
}
class up extends JFrame implements ActionListener {
	 static JTextField jtu1;static JTextField jtu2;
	    static JLabel lpicture;
			public void actionPerformed (ActionEvent e){  

			        setTitle("进行拍卖");
			        setLocationRelativeTo(null); // Center the frame
			        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        setSize(400, 400);
			              
			        setLayout(new GridLayout(4,1));  
			      //第一行选择名字
			        JPanel jp1=new JPanel();
			        jp1.setLayout(new GridLayout(2,1));
			        JLabel jlu1=new JLabel();
			        jlu1.setText("<html><p>请输入拍卖名称(尽量简洁)</p></html>");
			        jtu1=new JTextField(10);
			        
			        jp1.add(jlu1);jp1.add(jtu1);
			      //第二行输入详细信息
			        JPanel jp2=new JPanel();
			        jp2.setLayout(new GridLayout(2,1));
			        JLabel jlu2=new JLabel();
			        jlu2.setText("<html><p>请输入商品详细信息</p></html>");
			        jtu2=new JTextField(20);			        
			        jp2.add(jlu2);jp2.add(jtu2);
			        
			      //第三行打印文件路径     
			        JPanel jp3=new JPanel();
			        jp3.setLayout(new GridLayout(2,1));
			        lpicture=new JLabel();
			        JButton picture=new JButton("选择图片");
			        
			        filechoo1 filechoo=new filechoo1();
			        picture.addActionListener(filechoo);
			         	        
			        jp3.add(picture);jp3.add(lpicture);
			       //最后一行按钮进行发布 
			        JButton jb=new JButton("发布商品");
			        goauc go=new goauc();
			        jb.addActionListener(go);
			        add(jp1);
			        add(jp2);
			        add(jp3);
			        add(jb);
			        setVisible(true);  		
	}
}
class filechoo1 extends JFrame implements ActionListener{
	
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
						up.lpicture.setText(fileChooser.getSelectedFile().getPath());
					}
				}
			});
			panel.add(button);
			add(panel, BorderLayout.NORTH);
			setVisible(true);
		}
	}


