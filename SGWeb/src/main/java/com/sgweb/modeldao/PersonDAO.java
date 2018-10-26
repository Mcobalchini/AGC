package com.sgweb.modeldao;

import java.util.List;

import javax.persistence.EntityManager;

import com.sgweb.connection.ConnectionFactory;
import com.sgweb.model.Person;

public class PersonDAO {
	public void save(Person p) {
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

	public void delete(Person p) {
		EntityManager em = new ConnectionFactory().getConnection();
		try {
			em.getTransaction().begin();
			p = em.find(Person.class, p.getID());
			em.remove(p);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
	}

	public Person findById(Integer id) {
		EntityManager em = new ConnectionFactory().getConnection();
		return em.find(Person.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Person> findAll() {
		EntityManager em = new ConnectionFactory().getConnection();
		List<Person> persons = null;
		try {
			persons = em.createQuery("from Pessoa").getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return persons;
	}
}
