package com.epam.library.dao.pool;

public class DBParameter {
	
	private DBParameter(){}
	
    public static final String DB_DRIVER="driver";
    public static final String DB_URL = "jdbc:mysql://localhost/test_DB_library?useUnicode=true&characterEncoding=UTF-8&characterSetResults=utf8&useSSL=false&connectionCollation=utf8_general_ci";
    public static final String DB_USER ="root";
    public static final String DB_PASSWORD="root";
}
