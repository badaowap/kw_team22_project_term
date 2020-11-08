package kwteam22.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the Customer database table.
 * 
 */
@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String phone;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private int level;

	private String name;

	// bi-directional many-to-many association to Menu
	@ManyToMany(mappedBy = "customers")
	private List<Menu> menus;

	// bi-directional many-to-one association to Bill
	@OneToMany(mappedBy = "customer")
	private List<Bill> bills;

	public Customer() {
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setCustomer(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setCustomer(null);

		return bill;
	}

	@Override
	public boolean equals(Object obj) {
		Customer cus = (Customer) obj;
		if (cus.getPhone().equalsIgnoreCase(phone) && cus.getName().equalsIgnoreCase(name))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return phone.hashCode();
	}

}