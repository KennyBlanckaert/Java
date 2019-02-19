/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import administration.SchoolService;
import entities.Course;
import entities.Student;
import entities.Teacher;
import java.util.List;

/**
 *
 * @author Kenny Blanckaert
 */
public class main { 
    public static void main(String[] args) {
        SchoolService service = new SchoolService();
        
        List<Teacher> teachers = service.getTeachers();
        for(Teacher teacher : teachers) {
            System.out.println(teacher.getId() + ": " + teacher.getFirstname() + " " + teacher.getLastname());
        }
        System.out.println();
        
        List<Student> students = service.getStudents();
        for(Student student : students) {
            System.out.println(student.getId() + ": " + student.getFirstname() + " " + student.getLastname());
        }
        System.out.println();
        
        List<Course> courses = service.getCourses();
        for(Course course : courses) {
            System.out.println(course.getId() + ": " + course.getName());
        }
        System.out.println();
    }
}
