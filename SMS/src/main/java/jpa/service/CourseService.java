package jpa.service;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

/*public class CourseService implements CourseDAO {

    private SessionFactory sessionFactory;

    public CourseService() {
        // Initialize the session factory
    	this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<Course> getAllCourses() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List<Course> courses = null;

            try {
                // Use HQL to fetch all courses from the database
                courses = session.createQuery("FROM Course", Course.class).list();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                // Log or handle the exception as needed.
            }

            return courses;
        }
    }

    public Course getCourseById(Integer cId) {
    	try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Course c WHERE c.cId = :cid"; // Correct the parameter name
            TypedQuery<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("cid", cId); // Match the parameter name to the query
            return query.getSingleResult(); // Use getResultList to return a list
        } catch (Exception e) {
            // Log or handle the exception as needed.
            return null;
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}*/
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CourseService implements CourseDAO {

	private SessionFactory sessionFactory;
	private CourseDAO courseDAO;

	public CourseService() {
		// Default constructor with no dependencies. Not recommended for production.
		// Consider using dependency injection for a production-ready application.
	}

	public CourseService(SessionFactory sessionFactory, CourseDAO courseDAO) {
		this.sessionFactory = sessionFactory;
		this.courseDAO = courseDAO;
	}

	public List<Course> getAllCourses() {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();

			try {
				Query<Course> query = session.createQuery("FROM Course", Course.class);
				List<Course> courses = query.list();
				transaction.commit();
				return courses;
			} catch (Exception e) {
				transaction.rollback();
				// Log or handle the exception as needed.
				throw e; // Re-throw the exception if needed for upper-level handling.
			}
		}
	}

	public Course getCourseById(Integer cId) {
		try (Session session = sessionFactory.openSession()) {
			String hql = "FROM Course C WHERE C.cId = :courseId";
			Query<Course> query = session.createQuery(hql, Course.class);
			query.setParameter("courseId", cId);
			return query.uniqueResult();
		} catch (Exception e) {
			// Log or handle the exception as needed.
			throw e;
		}
	}

	public void close() {
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
		}
	}
}