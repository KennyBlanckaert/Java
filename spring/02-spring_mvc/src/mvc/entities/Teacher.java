package mvc.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import mvc.annotations.CourseCode;

public class Teacher {

	// Fields
	private String firstName;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String lastName;
	
	@NotNull(message="is required")
	@Min(value=0, message="invalid amount")
	@Max(value=3, message="max. 3 members allowed")
	private Integer familyMembers;
	
	@NotNull(message="is required")
	@Pattern(regexp="^[a-zA-Z]{3}[0-9]{5}$", message="recheck your teacherID! Something is wrong")
	private String teacherID;
	
	// Custom annotation:
	//		create class @interface CourseCode (@Constraint, @Target, @Retention)
	//		add methods for value & message
	//		include groups and payloads
	//		write CourseCodeConstraintValidator which checks the prefix in the isValid() method
	@CourseCode(value= {"EL", "INF"}, message="course codes must start with EL or INF")
	private String courseCode;
	
	// Default constructor
	public Teacher() {
		
	}

	// Getters
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getFamilyMembers() {
		return familyMembers;
	}

	public String getTeacherID() {
		return teacherID;
	}
	
	public String getCourseCode() {
		return courseCode;
	}

	// Setters
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setFamilyMembers(Integer familyMembers) {
		this.familyMembers = familyMembers;
	}
	
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
}
