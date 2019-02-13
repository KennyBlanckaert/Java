package hibernate.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	// Uni-direction relationship: each course contains one teacher (@OneToOne only in Course.class)
	// Bi-direction relationship: + each teacher contains one course (@OneToOne in Teacher.class and Course.class)
	// @OneToOne(cascade=CascadeType.DETACH)
	// @JoinColumn(name="teacherID")
	// private Teacher teacher;
	
	// Multiple courses belong to one teacher & one teacher belongs to a course
	// (@OneToMany in Teacher.class and @ManyToOne in Course.class)
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name="teacherID")
	private Teacher teacher;

	// Default constructor
	public Course() { }
	
	// Constructor
	public Course(String name) {
		this.name = name;
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

	// Functions
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}	
	
	
}
