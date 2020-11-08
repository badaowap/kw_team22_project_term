package kwteam22.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Bill database table.
 * 
 */
@Entity
@NamedQuery(name="Bill.findAll", query="SELECT b FROM Bill b")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int total;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	public Bill() {
	}
	
	public Bill(Customer customer, int total) {
		this.customer = customer;
		this.total = total;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}