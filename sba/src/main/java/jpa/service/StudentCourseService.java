package jpa.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourse;

public class StudentCourseService {
    
	   public void registerStudentToCourse(Session session, String studentEmail, int courseId) {
	        Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();

	            Student student = session.get(Student.class, studentEmail);
	            Course course = session.get(Course.class, courseId);

	            if (student != null && course != null) {
	                StudentCourse studentCourse = new StudentCourse(student, course);
	                session.save(studentCourse);

	                transaction.commit();
	            }
	        } catch (HibernateException e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }

	    public List<StudentCourse> getAllStudentCourses(Session session, String studentEmail) {
	        return session.createNamedQuery("CoursesByStudent", StudentCourse.class)
	            .setParameter("email", studentEmail)
	            .getResultList();
	    }
	}
