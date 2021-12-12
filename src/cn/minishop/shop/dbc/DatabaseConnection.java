package cn.minishop.shop.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/minishop";
	private static final String USER = "jamesmok";
	private static final String PASSWORD = "Tpmp90684213>.<";

	private Connection conn;

	public DatabaseConnection() {
		try {
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return this.conn ;
	}
	public void close() {
		if (this.conn != null) {
			try {
				this.conn.close() ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
