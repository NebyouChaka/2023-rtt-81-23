package jpa.entitymodels;

import java.util.List;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Column(name = "id")
	private Integer sid;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "email", unique = true)
	private String sEmail;

	@Column(name = "name")
	private String sName;

	@Column(name = "password")
	private String sPass;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "email_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> sCourses;


	public Student() {
		// Default constructor
	}
	// ------------Get/Set------------------//

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public List<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}

}