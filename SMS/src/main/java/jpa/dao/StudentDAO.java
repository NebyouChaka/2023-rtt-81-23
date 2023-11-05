package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;



public interface StudentDAO {
    List<Student> getAllStudents();

    Student getStudentByEmail(String email);

    boolean validateStudent(String email, String password);

    void registerStudentToCourse(String studentEmail, int courseId);

    List<Course> getStudentCourses(String studentEmail);

	void updateStudent(Student student);
    
}
