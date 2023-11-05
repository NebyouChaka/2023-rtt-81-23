package jpa.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {
	private SessionFactory sessionFactory;
	private CourseDAO courseDAO;

	public StudentService() {
		this.sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	// Second constructor to initialize every private member with provided
	// parameters
	public StudentService(SessionFactory sessionFactory, CourseDAO courseDAO) {
		if (sessionFactory == null) {
			throw new IllegalArgumentException("sessionFactory cannot be null");
		}

		if (courseDAO == null) {
			throw new IllegalArgumentException("courseDAO cannot be null");
		}

		this.sessionFactory = sessionFactory; // Use the provided sessionFactory
		this.courseDAO = courseDAO;
	}

	public List<Student> getAllStudents() {
		try (Session session = sessionFactory.openSession()) {
			String hql = "FROM Student";
			return session.createQuery(hql, Student.class).list();
		}
	}

	public Student getStudentByEmail(String sEmail) {
		try (Session session = sessionFactory.openSession()) {
			String hql = "FROM Student s WHERE s.sEmail = :email";
			Query<Student> query = session.createQuery(hql, Student.class);
			query.setParameter("email", sEmail);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// return studentDAO.getStudentByEmail(sEmail);
	}

	public boolean validateStudent(String sEmail, String sPassword) {

		Student student = getStudentByEmail(sEmail);
		return student != null && student.getsPass().equals(sPassword);
	}

	public void registerStudentToCourse(String sEmail, int cId) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			Student student = getStudentByEmail(sEmail);
			Course course = courseDAO.getCourseById(cId);

			if (student != null && course != null) {
				String hql = "SELECT COUNT(*) FROM Student s JOIN s.sCourses c WHERE s.sEmail = :email AND c.cId = :courseId";
				Long count = session.createQuery(hql, Long.class).setParameter("email", sEmail)
						.setParameter("courseId", cId).getSingleResult();

				if (count == 0) {
					student.getsCourses().add(course);
					session.update(student);
				}

			}

			session.getTransaction().commit();
		}
	}

	public List<Course> getStudentCourses(String sEmail) {
		try (Session session = sessionFactory.openSession()) {
			String hql = "SELECT c FROM Student s JOIN s.sCourses c WHERE s.sEmail = :email";
			Query<Course> query = session.createQuery(hql, Course.class);
			query.setParameter("email", sEmail);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateStudent(Student student) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(student);
			session.getTransaction().commit();
		}
	}

}