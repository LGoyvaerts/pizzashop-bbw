package ch.bbw.pizzashop;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbw.pizzashop.dao.PizzaShopDAO;

@Named("user")
@SessionScoped
public class UserBean implements Serializable {

	public UserBean() {
	}

	public UserBean(String firstname, String lastname, String email, String password, String street, Integer postcode,
			String city, boolean isAdmin) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
		this.isAdmin = isAdmin;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 23075552319700185L;

	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String street;
	private Integer postcode;
	private String city;
	private boolean isAdmin;

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

	public Integer getPostcode() {
		if (postcode == null) {
			return 0;
		}
		return postcode;
	}

	public void setPostcode(Integer postcode) {
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String createUser() throws Exception {
		UserBean userBean = new UserBean();
		userBean.setFirstname(firstname);
		userBean.setLastname(lastname);
		userBean.setEmail(email);
		userBean.setPassword(password);
		userBean.setStreet(street);
		userBean.setPostcode(postcode);
		userBean.setCity(city);
		userBean.setAdmin(isAdmin);
		pizzashopdao.createUser(userBean);
		return "/pizzashop.xhtml?faces-redirect=true";
	}

}
