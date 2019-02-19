package hibernate.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="courses")	
public class Course {

	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	// always created the appropriate getters/setters
	
	// Uni-direction relationship: each course contains one teacher (@OneToOne only in Course.class)
	// Bi-direction relationship: + each teacher contains one course (@OneToOne in Teacher.class and Course.class)
	//
	// @OneToOne(cascade=CascadeType.DETACH)
	// @JoinColumn(name="teacherID")
	// private Teacher teacher;
	
	// Multiple courses belong to a teacher & one teacher belongs to a course
	// (@OneToMany in Teacher.class and @ManyToOne in Course.class)
	@ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name="teacherID")
	private Teacher teacher;
	
	// Course can have multiple students, Student can have multiple courses
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinTable(
			name="courses_has_students",
			joinColumns=@JoinColumn(name="courses_id"),
			inverseJoinColumns=@JoinColumn(name="students_id")
	)
	private List<Student> students;

	// Default constructor
	public Course() {
		this.students = new ArrayList<>();
	}
	
	// Constructor
	public Course(String name) {
		this.name = name;
		this.students = new ArrayList<>();
	}

	// Getters
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public List<Student> getStudents() {
		return students;
	}



	// Setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	// Functions
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}	
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
}
