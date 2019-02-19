package hibernate.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="students")	
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	/* Student has multiple courses, Course can have multiple students*/
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinTable(
			name="courses_has_students",
			joinColumns=@JoinColumn(name="students_id"),
			inverseJoinColumns=@JoinColumn(name="courses_id")
	)
	private List<Course> courses;
	
	// Default constructor
	public Student() { 
		this.courses = new ArrayList<>();
	}
	
	// Constructor
	public Student(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.courses = new ArrayList<>();
	}

	// Getters
	public Integer getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
	
	public List<Course> getCourses() {
		return courses;
	}



	// Setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	// Functions
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
}
