package jpa.entitymodels;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cId")
    private int id;  

    @Column(name = "cName")
    private String name;   

    @Column(name = "cInstructorName")
    private String instructorName;  

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    public Course() {
        // Default constructor with no parameters
    }

    public Course(String name, String instructorName) {
        this.name = name;
        this.instructorName = instructorName;
    }

    // Getters and setters for all private members
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
