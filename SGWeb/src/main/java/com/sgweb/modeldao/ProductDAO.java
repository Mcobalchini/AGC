package com.sgweb.modeldao;

import java.util.List;

import javax.persistence.EntityManager;

import com.sgweb.connection.ConnectionFactory;
import com.sgweb.model.Product;

public class ProductDAO {
	public void save(Product p) {
		EntityManager em = new ConnectionFactory().getConnection();

		try {
			em.getTransaction().begin();
			if (p.getID() == null) {
				em.persist(p);
			} else {
				em.merge(p);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void delete(Product p) {
		EntityManager em = new ConnectionFactory().getConnection();
		try {
			em.getTransaction().begin();
			p = em.find(Product.class, p.getID());
			em.remove(p);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public Product findById(Integer id) {
		EntityManager em = new ConnectionFactory().getConnection();
		return em.find(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		EntityManager em = new ConnectionFactory().getConnection();
		List<Product> products = null;
		try {
			products = em.createQuery("from Produto").getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return products;
	}
}
