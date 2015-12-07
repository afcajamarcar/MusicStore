package co.edu.unal.musicstore.many2many.ofy;
import java.io.Serializable;
import java.util.*;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
@Entity
public class ShoppingCart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6039135105949527823L;
	@Id public String id;
	private ArrayList<Product> products;
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public ShoppingCart() {
	}
	public void addProducts(Product product){
		products.add(product);
	}
	public void empty(){
		if(products.isEmpty()){
			System.out.println("No hay productos");
		}else{
			for(Product item : products){
				products.remove(item);
			}
		}
	}
}
