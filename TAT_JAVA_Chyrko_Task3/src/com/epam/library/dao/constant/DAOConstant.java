package com.epam.library.dao.constant;

public class DAOConstant {	
	public final static String DELIMITER = "&";
	public final static String LOCATION_OF_JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
	public final static String JDBC_URL = "jdbc:mysql://localhost/test_DB_library?"
			+ "useUnicode=true&characterEncoding=UTF-8&characterSetResults=utf8&"
			+ "useSSL=false&connectionCollation=utf8_general_ci";
	public final static String DB_PASSWORD = "root";
	public final static String DB_USERNAME = "root";
	public final static long WAITING_TIMEOUT_SEC = 10;
	public final static int DEFAULT_POOL_SIZE = 10;
}
