package org.perscholas.database.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.perscholas.database.entity.Order;
import org.perscholas.database.entity.Product;

public class ProductDAO {
	
	private SessionFactory factory = new Configuration().configure().buildSessionFactory();
	private Session session = factory.openSession();
	

	public Product findById(Integer id) {
		String hql = "FROM Product p WHERE p.id = :id"; // Example of HQL to get all records of user class

		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setParameter("id", id);

		try {
			Product result = query.getSingleResult();	
			return result;
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	public List<Product> findByName(String name) {
		String hql = "FROM Product p WHERE p.productName like :name"; // Example of HQL to get all records of user class

		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setParameter("name", "%" + name + "%");
		List<Product> result = query.getResultList();
		
		return result;
	}
	
	
	public void save(Product product) {
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(product);
		t.commit();
	}
	
}
