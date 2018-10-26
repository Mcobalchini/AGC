package com.sgweb.modeldao;

import com.sgweb.connection.ConnectionFactory;
import java.util.List;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import com.sgweb.model.Employee;

public class EmployeeDAO {

    public void save(Employee e) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            if (e.getID() == null) {
                em.persist(e);
            } else {
                em.merge(e);
            }
            em.getTransaction().commit();
        } catch (Exception f) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void delete(Employee e) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            em.getTransaction().begin();
            e = em.find(Employee.class, e.getID());
            em.remove(e);
            em.getTransaction().commit();
        } catch (Exception f) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Employee findById(Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();
        return em.find(Employee.class, id);
    }

    @SuppressWarnings("unchecked")
	public List<Employee> findAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Employee> Employees = new ArrayList<>();
        try {
            Employees = em.createQuery("from Employee").getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return Employees;
    }

}


