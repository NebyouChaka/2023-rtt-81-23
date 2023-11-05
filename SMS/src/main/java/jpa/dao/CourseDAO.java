package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public interface CourseDAO {
	
	List<Course> getAllCourses();

	Course getCourseById(Integer cId);
}
