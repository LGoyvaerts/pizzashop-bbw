package ch.bbw.pizzashop.dao;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ch.bbw.pizzashop.UserBean;
import ch.bbw.pizzashop.entity.Pizza;

@Named("pizzadao")
@RequestScoped
public class PizzaShopDAO implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = -6278259462552289200L;
	private static Logger log = Logger.getLogger(PizzaShopDAO.class.getSimpleName());

	private static String url = "jdbc:mysql://localhost:3306/pizzashop?useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true";
	private static String user = "root";
	private static String password = "12345678";
	List<Pizza> pizzas = new ArrayList<>();

	// Die Datenbank connection
	Connection connection;

	@PostConstruct
	private void init() {
		log.info("-------- MySQL JDBC Connection Testing ------------");

		try {
			// Treiber-Klasse Laden
			Class.forName("com.mysql.jdbc.Driver");
			log.info("the driver is loaded");
		} catch (ClassNotFoundException e) {
			log.info("Where is your MySQL JDBC Driver?");
			e.printStackTrace();

		}

		log.info("MySQL JDBC Driver Registered!");
		connection = null;

		try {
			// Init Connection
			connection = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			log.info("Connection Failed! Check output console");
			e.printStackTrace();

		}

		if (connection != null) {
			log.info("You made it, take control your database now!");
		} else {
			log.info("Failed to make connection!");

		}
	}

	public List<Pizza> getPizzas() throws Exception {
		try (Statement statement = connection.createStatement()) {

			String sql = "SELECT * FROM pizzas;";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				Pizza pizza = new Pizza(rs.getInt("ID"), rs.getString("name"), rs.getFloat("price"),
						rs.getString("imagepath"));
				pizzas.add(pizza);
			}
			return pizzas;
		}

	}

	public void createUser(UserBean user) throws Exception {
		try (Statement statement = connection.createStatement()) {
			int isAdmin = 0;
			if (user.isAdmin()) {
				isAdmin = 1;
			}
			String hashedPassword = "";
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
			hashedPassword = hash.toString();
			String sql = "INSERT INTO users(firstname, lastname, email, street, postcode, city, password, isadmin) VALUES('"
					+ user.getFirstname() + "', '" + user.getLastname() + "', '" + user.getEmail() + "', '"
					+ user.getStreet() + "', '" + user.getPostcode() + "', '" + user.getCity() + "', '" + hashedPassword
					+ "', '" + isAdmin + "');";
			statement.executeUpdate(sql);
			log.info("USER CREATED: " + user.getFirstname() + ", " + user.getLastname());
		}
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	@PreDestroy
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
