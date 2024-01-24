package connectionutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.CourierServiceDb;

public class DBConnection {
	private static Connection connection;

	
	private DBConnection() {
	}

	public static Connection getConnection() {
		if (connection == null) {
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");


				String connectionUrl = CourierServiceDb.getPropertyString();

			
				connection = DriverManager.getConnection(connectionUrl);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Error connecting to the database");
			}
		}
		return connection;
	}
}