package vn.vimass.csdl.utilDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	public final static String URL = "jdbc:mysql://localhost:3306/vimass_db?useUnicode=yes&characterEncoding=UTF-8";
	public final static String USER = "root";
	public final static String PASS = "root";
	
	
	public static Connection getConnect(String url, String user, String pass) {
		try {
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
			return connection;
		} catch (Exception e) {
			System.out.println("Exception" + e.toString());
			return null;
		}
	}
}
