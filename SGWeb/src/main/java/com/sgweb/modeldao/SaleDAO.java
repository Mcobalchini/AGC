package com.sgweb.modeldao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.sgweb.connection.ConnectionFactory;
import com.sgweb.model.ClosedSale;
import com.sgweb.model.Sale;

public class SaleDAO {
	public void save(Sale s) {

		EntityManager em = new ConnectionFactory().getConnection();

		try {

			em.getTransaction().begin();
			if (s.getID() == null) {
				em.persist(s);
			} else {
				em.merge(s);
			}

			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();

		} finally {

			em.close();

		}

	}

	public void delete(Sale s) {

		EntityManager em = new ConnectionFactory().getConnection();

		try {

			em.getTransaction().begin();
			s = em.find(Sale.class, s.getID());
			em.remove(s);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();

		} finally {

			em.close();

		}
	}

	public Sale findById(Integer id) {

		EntityManager em = new ConnectionFactory().getConnection();
		return em.find(Sale.class, id);

	}

	@SuppressWarnings("unchecked")
	public List<Sale> findAll() {

		EntityManager em = new ConnectionFactory().getConnection();
		List<Sale> sells = null;
		try {

			sells = em.createQuery("from Venda").getResultList();
		} catch (Exception e) {

			System.out.println(e);

		} finally {

			em.close();

		}

		return sells;

	}

	public void finalizarVenda(Sale s) {

		ClosedSale cs = new ClosedSale();
		ClosedSaleDAO csdao = new ClosedSaleDAO();

		cs.setSale(s);
		cs.setDate(getDateTime());
		csdao.save(cs);

	}

	private Date getDateTime() {

		Date date = new Date();
		return date;

	}
}
