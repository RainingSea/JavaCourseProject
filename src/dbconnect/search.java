package dbconnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

import com.sun.jdi.connect.spi.Connection;

public class search {
		String a,b;
		public static void main(String args[]){
			// TODO 自动生成的方法存根
			
			java.sql.Connection conn=db.getConshop();
			
			try{
				System.out.println("搜索");
				Statement s = conn.createStatement();
		//		String sql = "select ipicture FROM all_shop limit 0,2";
				String sql = "select * from all_shop where iname like '%饼%' ";
				ResultSet re=s.executeQuery(sql);
				while(re.next()) {	
				System.out.println(re.getString(2)+"  结果");
				//re返回的是一整行数据，getString()括号里控制第几列数据
				
				String r=re.getString(1);	
			//	JFrame jf=new JFrame();
//		        JPanel jp=new JPanel(){
//		        public void paintComponent(Graphics g) {
//	                super.paintComponent(g);
//	                ImageIcon ii = new ImageIcon(r);
//	                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
//	            }
//	        };
//	            jf.add(jp);
//				jf.setSize(200, 200);
//				jf.setVisible(true);
//				i++;
				}	
				re.close();
				s.close();
				conn.close();
				
			}
			catch(SQLException e){
				
			}
		}
		
		}


