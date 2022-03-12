package Listener;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
 
import javax.swing.*;
 
public class filechoo extends JFrame{
	
	private JTextField textField;
	private JPanel panel = new JPanel();
	private JFileChooser fileChooser = new JFileChooser();
	
	public filechoo() {
		setTitle("选项卡面板");
		setBounds(400, 400, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
					textField.setText(selectedFile.getName());// 显示选中文件的名称
					System.out.println(fileChooser.getSelectedFile().getPath());
				}
			}
		});
		panel.add(button);
		
		add(panel, BorderLayout.NORTH);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		filechoo test = new filechoo();
 
	}
 
}

