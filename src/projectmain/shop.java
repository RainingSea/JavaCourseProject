package projectmain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.InputStream;
import dbconnect.ImageUtil;

public class shop implements java.io.Serializable {  
    private static final long serialVersionUID = 1L;  
    String type,name,own,path;
    byte[] bytes;
    FileInputStream fin;
    public shop(String ty,String na,String ow,String path) throws FileNotFoundException {  
    	type=ty;
    	name=na;
    	own=ow;
        
        bytes = null;
		try {
			fin = new FileInputStream(new File(path));
			bytes  = new byte[fin.available()];  
	        //将文件内容写入字节数组
	        fin.read(bytes);  
	        fin.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}  
    }     
    public byte[] get() {
		return bytes;	
    }

} 

