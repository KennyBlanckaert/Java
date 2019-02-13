package hibernate.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	// Uni-direction relationship: each course contains a teacher (@OneToOne only in Course.class)
	// Bi-direction relationship: each teacher contains a course (@OneToOne in Teacher.class and Course.class)
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="teacherID")
	private Teacher teacher;
	
	/* other relationships:
	 * 		
	 * 			@OneToMany & @ManyToOne
	 * 			
	 */

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
