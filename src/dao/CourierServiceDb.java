package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CourierServiceDb {
	private CourierServiceDb() {
	}

	public static String getPropertyString() {
		Properties properties = new Properties();
		try (InputStream fis = CourierServiceDb.class.getClassLoader().getResourceAsStream("db.properties")) {
			if (fis == null) {
				throw new IOException("db.properties not found in the classpath");
			}
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error loading db.properties");
		}

		String hostname = properties.getProperty("hostname");
		String dbname = properties.getProperty("dbname");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String port = properties.getProperty("port");

		if (hostname == null || port == null) {
			throw new RuntimeException("Hostname or port is missing in db.properties");
		}

		
		return "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;
	}
}
