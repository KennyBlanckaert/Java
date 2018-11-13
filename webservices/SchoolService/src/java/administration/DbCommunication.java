/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import entities.Course;
import entities.Student;
import entities.Teacher;
import java.sql.PreparedStatement;
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

    public static List<Course> getCoursesFromDatabase() {
        
        List<Course> courses = new ArrayList<>();
        
        if (connection == null) {
            connection = DatabaseConfig.openConnection();
        }
        
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM courses");
            while(result.next()) {
                Course course = new Course(result.getInt(1), result.getString(2));
                courses.add(course);
            }
        }
        catch (SQLException error) { /* What to do if the query fails? */ }
        
        return courses;
    }

    public static List<Teacher> getTeachersFromDatabase() {
        
        List<Teacher> teachers = new ArrayList<>();
        
        if (connection == null) {
            connection = DatabaseConfig.openConnection();
        }
        
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM teachers");
            while(result.next()) {
                Teacher teacher = new Teacher(result.getInt(1), result.getString(2), result.getString(3));
                teachers.add(teacher);
            }
        }
        catch (SQLException error) { /* What to do if the query fails? */ }
        
        return teachers;
    }

    public static boolean addStudentToDatabase(String firstname, String lastname) {
        
        if (connection == null) {
            connection = DatabaseConfig.openConnection();
        }
        
        String query = "INSERT INTO students (firstname, lastname) values (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.executeUpdate();
        }
        catch (SQLException error) {
            return false;
        }
        
        return true;
    }

    public static boolean addCourseToDatabase(String name) {
        
        if (connection == null) {
            connection = DatabaseConfig.openConnection();
        }
        
        String query = "INSERT INTO courses (name) values (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
        catch (SQLException error) {
            return false;
        }
        
        return true;
    }

    static boolean addTeacherToDatabase(String firstname, String lastname) {
        
        if (connection == null) {
            connection = DatabaseConfig.openConnection();
        }
        
        String query = "INSERT INTO teachers (firstname, lastname) values (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.executeUpdate();
        }
        catch (SQLException error) {
            return false;
        }
        
        return true;
    }
}
