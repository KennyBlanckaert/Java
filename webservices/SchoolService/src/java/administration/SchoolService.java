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
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author Kenny Blanckaert
 */
@WebService(serviceName = "SchoolService")
public class SchoolService {

    @WebMethod(operationName = "getStudents")
    @WebResult(name="Student")
    public List<Student> getStudents() {
        return DbCommunication.getStudentFromDatabase();
    }
}
