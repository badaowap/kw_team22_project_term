package kwteam22.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Basket database table.
 * 
 */
@Entity
@NamedQuery(name="Basket.findAll", query="SELECT b FROM Basket b")
public class Basket implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BasketPK id;

	public Basket() {
	}

	public BasketPK getId() {
		return this.id;
	}

	public void setId(BasketPK id) {
		this.id = id;
	}

}