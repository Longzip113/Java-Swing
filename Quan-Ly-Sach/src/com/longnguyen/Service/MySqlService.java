package com.longnguyen.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class MySqlService {
	protected Connection conn = null; 
	public MySqlService() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/QUANLYSACH";
		Properties pro = new Properties();
		pro.put("user", "root");
		pro.put("password", "long250599");
    	conn = DriverManager.getConnection(url,pro);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if(conn != null)
	      {
	    	  System.out.println("Ket noi thanh cong");
	      }
	      else {
			System.out.println("Ket noi that bai");
		}
	}
}
