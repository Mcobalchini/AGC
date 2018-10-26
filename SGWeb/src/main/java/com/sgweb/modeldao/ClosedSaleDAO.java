package com.sgweb.modeldao;

import java.util.List;

import javax.persistence.EntityManager;

import com.sgweb.connection.ConnectionFactory;
import com.sgweb.model.ClosedSale;

public class ClosedSaleDAO {
	public void save(ClosedSale cs) {

		EntityManager em = new ConnectionFactory().getConnection();

		try {

			em.getTransaction().begin();
			if (cs.getID() == null) {
				em.persist(cs);
			} else {
				em.merge(cs);
			}

			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();

		} finally {

			em.close();

		}

	}

	public void delete(ClosedSale cs) {

		EntityManager em = new ConnectionFactory().getConnection();

		try {

			em.getTransaction().begin();
			cs = em.find(ClosedSale.class, cs.getID());
			em.remove(cs);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();

		} finally {

			em.close();

		}
	}

	public ClosedSale findById(Integer id) {

		EntityManager em = new ConnectionFactory().getConnection();
		return em.find(ClosedSale.class, id);

	}

	@SuppressWarnings("unchecked")
	public List<ClosedSale> findAll() {

		EntityManager em = new ConnectionFactory().getConnection();
		List<ClosedSale> closedSales = null;
		try {

			closedSales = em.createQuery("from VendaFinalizada").getResultList();
		} catch (Exception e) {

			System.out.println(e);

		} finally {

			em.close();

		}

		return closedSales;

	}
}
