package jpa.dao;

import java.util.List;
import jpa.entitymodels.Student;
import jpa.entitymodels.Course;

public interface StudentDAO {
	
	List<Student> getAllStudents();

	Student getStudentByEmail(String email);

	boolean validateStudent(String email, String password);

	boolean registerStudentToCourse(String email, int courseId);

	List<Course> getStudentCourses(String email);
}
