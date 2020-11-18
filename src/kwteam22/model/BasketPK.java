package kwteam22.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Basket database table.
 * 
 */
@Embeddable
public class BasketPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="menu_id", insertable=false, updatable=false)
	private String menuId;

	@Column(name="customer_phone", insertable=false, updatable=false)
	private String customerPhone;

	public BasketPK() {
	}
	public String getMenuId() {
		return this.menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getCustomerPhone() {
		return this.customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BasketPK)) {
			return false;
		}
		BasketPK castOther = (BasketPK)other;
		return 
			this.menuId.equals(castOther.menuId)
			&& this.customerPhone.equals(castOther.customerPhone);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.menuId.hashCode();
		hash = hash * prime + this.customerPhone.hashCode();
		
		return hash;
	}
}