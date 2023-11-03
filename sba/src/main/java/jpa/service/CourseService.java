package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

import java.util.List;

public class CourseService {
    private final CourseDAO courseDAO;

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

	public static Course getCourseById(int courseID) {
		// TODO Auto-generated method stub
		return null;
	}
}