package ch.bbw.pizzashop.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= "pizza")
public class Pizza {

	private int id;
	private String name;
	private float price;
	private String imagepath;
	
	public Pizza(int id, String name, float price, String imagepath) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imagepath = imagepath;
	}

	@XmlElement(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "price")
	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@XmlElement(name = "imagepath")
	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	
	
	
}
