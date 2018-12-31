package org.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCUtils {
	private static Properties prop=null;
	static{
		prop=new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			Class.forName(prop.getProperty("mysqlDriver"));
			return DriverManager.getConnection(
			prop.getProperty("mysqlURL"),prop.getProperty("mysqlUser"),prop.getProperty("mysqlPwd"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void Closeall(AutoCloseable... states){
		for (AutoCloseable state : states) {
			try {
				if(state!=null)
					state.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}