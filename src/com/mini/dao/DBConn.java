package com.mini.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		Connection conn = null;
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/miniprj?serverTimezone=UTC";
		String dUser = "root";
		String dPwd = "1234";

		Class.forName(jdbcDriver);
		conn = DriverManager.getConnection(jdbcUrl, dUser, dPwd);
		
		return conn;
	}
	
}
