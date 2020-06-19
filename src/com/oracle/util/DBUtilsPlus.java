package com.oracle.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtilsPlus {
	
     private static String driver=null;
     
     private static String url=null;
     
     private static String username=null;
     
     private static String password=null;
     
     
     static{
    	 
    	 
    	 //1.创建一个properties对象
    	 
    	 Properties p=new Properties();
    	 
    	 InputStream in=null;
    	 
    	 try {
    		 //2.创建一个输入流
    		 //in=new FileInputStream("jdbc.properties");
			in=DBUtilsPlus.class.getClassLoader().getResourceAsStream("jdbc.properties");//以类加载器的方式加载properties文件
			 
			//3.加载properties文件
			p.load(in);
			
			//4.获取文件值，并赋值给上面的4个变量
			driver=p.getProperty("driverClass");
			
			url=p.getProperty("url");
			
			username=p.getProperty("userName");
			
			password=p.getProperty("password");
			
			//5.注册驱动
			Class.forName(driver);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			
			if(in!=null){
				
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	 
    	 
     }
     
     
   //1.得到链接
     
     public static Connection getConnection() throws SQLException{
     	
     	
     	return DriverManager.getConnection(url, username, password);
     }
     
     //2.关闭资源
     
     public static void close(Connection conn,Statement stmt){
     	
     	closeStmt(stmt);
     	
     	closeConn(conn);
     	
     }
     
     private static void closeConn(Connection conn) {
 	
     	if(conn!=null){
     		
     		try {
 				conn.close();
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
     	}
 		
 	}

 	private static void closeStmt(Statement stmt) {
 		// TODO Auto-generated method stub
 		
 		if(stmt!=null){
 			
 			try {
 				stmt.close();
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 		}
 		
 	}

 	
     
     public static void close(Connection conn,Statement stmt,ResultSet rs){
     	
     	closeRs(rs);
     	
     	closeStmt(stmt);
     	
     	closeConn(conn);
     	
     }

 	private static void closeRs(ResultSet rs) {
 		// TODO Auto-generated method stub
 		
 		if(rs!=null){
 			
 			try {
 				rs.close();
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 		}
 	}

}
