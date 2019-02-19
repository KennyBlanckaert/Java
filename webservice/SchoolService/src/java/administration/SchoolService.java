/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import entities.Course;
import entities.Student;
import entities.Teacher;
import java.util.List;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author Kenny Blanckaert
 */
@WebService(serviceName = "SchoolService")
@HandlerChain(file = "SchoolService_handler.xml")
public class SchoolService {

    // Get students
    @WebMethod(operationName = "getStudents")
    @WebResult(name="Student")
    public List<Student> getStudents() {
        return DbCommunication.getStudentFromDatabase();
    }
    
    // Add student
    @WebMethod(operationName = "addStudent")
    public boolean addStudent(@WebParam(name = "firstname") String firstname, @WebParam(name = "lastname") String lastname) {
        return DbCommunication.addStudentToDatabase(firstname, lastname);
    }
   
    // Get courses
    @WebMethod(operationName = "getCourses")
    @WebResult(name="Course")
    public List<Course> getCourses() {
        return DbCommunication.getCoursesFromDatabase();
    }
    
    // Add course
    @WebMethod(operationName = "addCourse")
    public boolean addCourse(@WebParam(name = "name") String name) {
        return DbCommunication.addCourseToDatabase(name);
    }
    
    // Get teachers
    @WebMethod(operationName = "getTeachers")
    @WebResult(name="Teacher")
    public List<Teacher> getTeachers() {
        return DbCommunication.getTeachersFromDatabase();
    }
    
    // Add teacher
    @WebMethod(operationName = "addTeacher")
    public boolean addTeacher(@WebParam(name = "firstname") String firstname, @WebParam(name = "lastname") String lastname) {
        return DbCommunication.addTeacherToDatabase(firstname, lastname);
    }
}
