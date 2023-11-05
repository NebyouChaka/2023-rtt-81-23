package jpa.entitymodels;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer cId;

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	@Column(name = "name")
	private String cName;

	@Column(name = "instructor")
	private String cInstructorName;
	
	// ------------Get/Set----------------//

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

	public String toString() {
		return String.format("%5d%15s%15s", cId, cName, cInstructorName);
	}

}