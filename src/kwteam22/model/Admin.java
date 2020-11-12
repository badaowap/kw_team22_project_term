package kwteam22.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Admin database table.
 * 
 */
@Entity
@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a")
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String user;

	private String password;

	public Admin() {
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
		public boolean equals(Object obj) {
			Admin admin = (Admin)obj;
			if(user.equals(admin.getUser()) && password.equals(admin.getPassword()))
				return true;
			return false;
		}
}