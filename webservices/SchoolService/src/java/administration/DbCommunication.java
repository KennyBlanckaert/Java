/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import entities.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kenny Blanckaert
 */
public class DbCommunication {
    
    private static Connection connection = null;
    
    public static List<Student> getStudentFromDatabase() {
        
        List<Student> students = new ArrayList<>();
        
        if (connection == null) {
            connection = DatabaseConfig.openConnection();
        }
        
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM students");
            while(result.next()) {
                Student student = new Student(result.getInt(1), result.getString(2), result.getString(3));
                students.add(student);
            }
        }
        catch (SQLException error) { /* What to do if the query fails? */ }
        
        return students;
    }
}
