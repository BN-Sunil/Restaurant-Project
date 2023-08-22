package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Dto.Customer;

public class MyDao {
	EntityManagerFactory e = Persistence.createEntityManagerFactory("dev");
	EntityManager m = e.createEntityManager();
	EntityTransaction t = m.getTransaction();

	public void save(Customer cust) {
		t.begin();
		m.persist(cust);
		t.commit();

	}

	public Customer fetchByEmail(String email) {
		
		List<Customer> list = m.createQuery("select x from Customer x where email=?1").setParameter(1, email).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list.get(0);

	}

	public Customer fetchByMobile(long phno) {
		List<Customer> list = m.createQuery("select x from Customer x where phonenumber=?1").setParameter(1, phno).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}



}
