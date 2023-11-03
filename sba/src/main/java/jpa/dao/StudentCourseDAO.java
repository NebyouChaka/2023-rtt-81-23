package jpa.dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourse;

import java.util.List;

public class StudentCourseDAO {
    
	public void registerStudentToCourse(Session session, String studentEmail, int courseId) {
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();

	        Student student = session.get(Student.class, studentEmail);
	        Course course = session.get(Course.class, courseId);

	        if (student != null && course != null) {
	            StudentCourse studentCourse = new StudentCourse();
	            studentCourse.setStudent(student);
	            studentCourse.setCourse(course);
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

	public List<Course> getStudentCourses(Session session, String studentEmail) {
	    // Use HQL (Hibernate Query Language) to fetch courses associated with the student
	    String hql = "select sc.course from StudentCourse sc where sc.student.email = :studentEmail";
	    return session.createQuery(hql, Course.class)
	        .setParameter("studentEmail", studentEmail)
	        .getResultList();
	}
    
}





