package kwteam22.view.template;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import kwteam22.model.Customer;

public class ConnToDB<T> implements Conn<T> {
	EntityManagerFactory emf = null;
	EntityManager em = null;
	EntityTransaction et = null;
	List<T> lists;

	public void getConnection() {
		try {
			emf = Persistence.createEntityManagerFactory("VendingMachine");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(T obj) {
		try {
			getConnection();
			em.persist(obj);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modify(Object ... obj) {
		getConnection();
		obj[0] = em.find(obj[0].getClass(), obj[1]);
		modifyCustomer(obj);
		em.merge(obj[0]);
		et.commit();
	}
	
	private void modifyCustomer(Object[] obj) {
		if(obj[0] instanceof Customer) {
			((Customer) obj[0]).setBirthday((Date)obj[2]);
			((Customer) obj[0]).setLevel((int) obj[3]);
		}
	}

	@Override
	public void remove(Object obj, String primaryKey) {
		getConnection();
		obj = em.find(Object.class, primaryKey);
		em.remove(obj);
		et.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(Query query, String sql) {
		getConnection();
		query = em.createQuery(sql);
		return lists = query.getResultList();

	}

}
