package hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {

	// For testing purpose only
	// See hibernate.cfg.xml for the config file
	public static void main(String[] args) {
		
		// serverTimezone to solve unrecognized timezone error
		String connection_string = "jdbc:mysql://localhost:3306/School?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String password = "Azerty123";
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connection_string, user, password);
			System.out.println("Connection successful");
		}
		catch (SQLException e){
			System.out.println("Could not connect to the database.");
			e.printStackTrace();
		}	
	}
}
