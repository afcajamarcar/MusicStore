package co.edu.unal.musicstore.many2many.ofy; 

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
@Entity
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6919285255137533251L;
	@Id private String id;

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		id = this.id;
	}
	
	public void buyProduct(Product product){
		System.out.println("Producto comprado:" + "" );
	}
}
