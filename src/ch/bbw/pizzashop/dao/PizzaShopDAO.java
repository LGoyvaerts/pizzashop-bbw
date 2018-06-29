package ch.bbw.pizzashop.dao;

import java.io.Serializable;
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
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import ch.bbw.pizzashop.entity.Pizza;

@Named("pizzadao")
@ApplicationScoped
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

			String sql = "SELECT id, name, price, imagepath FROM pizzas;";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				Pizza pizza = new Pizza(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"),
						rs.getString("imagepath"));
				pizzas.add(pizza);
			}
			return pizzas;
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
