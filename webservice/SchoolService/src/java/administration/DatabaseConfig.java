/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kenny Blanckaert
 */
public class DatabaseConfig {
    
    // Default settings
    private static final String DB_URL = "jdbc:mysql://localhost:3306/School?autoReconnect=true&useSSL=false";
    private static final String DB_DRV = "com.mysql.jdbc.Driver";
    private static final String DB_USER = "Kenny";
    private static final String DB_PASSWD = "Azerty123";
    
    // Functions
    public static Connection openConnection() {
        
        Connection connection = null;
        
        try {
            connection = (Connection) DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
        }
        catch(SQLException ex) { /* What to do if connection fails? */ }
        
        return connection;
    }
}
