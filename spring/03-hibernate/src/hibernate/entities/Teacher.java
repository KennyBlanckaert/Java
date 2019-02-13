package hibernate.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="teachers")
public class Teacher {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	// Uni-direction relationship: each course contains a teacher (@OneToOne only in Course.class)
	// Bi-direction relationship: each teacher contains a course (@OneToOne in Teacher.class and Course.class)
	//	@OneToOne(mappedBy="teacher", cascade=CascadeType.DETACH)
	//	private Course course;
	
	// Uni-direction relationship: multiple teacher belong to one course
	// Bi-direction relationship: each teacher contains mulitple courses 
	// (@OneToMany in Teacher.class and @ManyToOne in Course.class)
	@OneToMany(mappedBy="teacher", cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	private List<Course> courses;
	
	// Default constructor
	public Teacher() { }
	
	// Constructor
	public Teacher(String firstname, String lastname) {
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
	public void addCourse(Course course) {
		this.courses.add(course);
		course.setTeacher(this);
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", courses=" + courses
				+ "]";
	}
}
