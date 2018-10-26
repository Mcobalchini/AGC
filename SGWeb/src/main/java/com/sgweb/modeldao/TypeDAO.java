package com.sgweb.modeldao;

import java.util.List;

import javax.persistence.EntityManager;

import com.sgweb.connection.ConnectionFactory;
import com.sgweb.model.Type;

public class TypeDAO {
	public void save(Type t) {
		EntityManager em = new ConnectionFactory().getConnection();

		try {
			em.getTransaction().begin();
			if (t.getID() == null) {
				em.persist(t);
			} else {
				em.merge(t);
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void delete(Type t) {
		EntityManager em = new ConnectionFactory().getConnection();
		try {
			em.getTransaction().begin();
			t = em.find(Type.class, t.getID());
			em.remove(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public Type findById(Integer id) {
		EntityManager em = new ConnectionFactory().getConnection();
		return em.find(Type.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Type> findAll() {
		EntityManager em = new ConnectionFactory().getConnection();
		List<Type> types = null;
		try {
			types = em.createQuery("from TipoProduto").getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return types;
	}
}
