package kwteam22.test;

import java.util.List;

import javax.persistence.Query;

import kwteam22.model.Customer;
import kwteam22.view.MenuView;
import kwteam22.view.template.Conn;
import kwteam22.view.template.ConnToDB;

public class Main {

	public static void main(String[] args) {

		MenuView menuView = new MenuView(false, null);
		menuView.setLocationRelativeTo(null);
		menuView.setVisible(true);

		/*
		 * String name = "ashino itachi"; String phone = "01022334455"; Customer cus =
		 * new Customer(); cus.setPhone(phone); cus.setName(name);
		 * 
		 * Conn<Customer> conn = new ConnToDB<Customer>(); Query query = null;
		 * List<Customer> lists = conn.query(query, "from Customer");
		 * if(lists.contains(cus)) System.out.println("My name is Nam");
		 */
		/*
		 * EntityManagerFactory emf = null; EntityManager em = null; EntityTransaction
		 * et = null; try { emf =
		 * Persistence.createEntityManagerFactory("VendingMachine"); em =
		 * emf.createEntityManager(); et = em.getTransaction(); et.begin();
		 * 
		 * Customer cus = new Customer(); cus = em.find(Customer.class, "00000000000");
		 * String str = null; Date date = new SimpleDateFormat("yyyy/MM/dd").parse(str);
		 * cus.setBirthday(date); em.merge(cus); et.commit();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}
}
