package com.epam.library.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionPool {
//	 private String driver;
	 private String url;
	 private String user;
	 private String password;
	 private Connection instance;
	 
	 public ConnectionPool(){		 
		 try {			
//			 this.driver = DBParameter.DB_DRIVER;
			 this.user =DBParameter.DB_USER;
	         this.url = DBParameter.DB_URL;
	         this.password = DBParameter.DB_PASSWORD;
			 instance = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			
		}		 
	 }
	 
	 public Connection getConnection(){
		 return instance;
	 }
	 
	 
}
