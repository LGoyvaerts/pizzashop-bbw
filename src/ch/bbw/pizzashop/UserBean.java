package ch.bbw.pizzashop;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbw.pizzashop.dao.PizzaShopDAO;

@Named("user")
@SessionScoped
public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 23075552319700185L;

	private String firstname;
	private String lastname;
	private String email;
	private String street;
	private int postcode;
	private String city;
	
	@Inject
	private PizzaShopDAO pizzashopdao;
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public int getPostcode() {
		return postcode;
	}
	
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String saveUser() throws ClassNotFoundException {
		
		return null;
	}

	public PizzaShopDAO getPizzashopdao() {
		return pizzashopdao;
	}

	public void setPizzashopdao(PizzaShopDAO pizzashopdao) {
		this.pizzashopdao = pizzashopdao;
	}
	
}
