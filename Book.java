package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b ORDER BY b.id")
public class Book implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	public Book(String name, String auth,String pb, float price){
		this.name = name;
		this.author = auth;
		this.publishing_house = pb;
		this.price = price;
	}
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private String author;

	private String name;

	private float price;

	@Column(name="`Publishing house`")
	private String publishing_house;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="book")
	private List<Orders> orders;

	//bi-directional many-to-one association to Supply
	@OneToMany(mappedBy="book")
	private List<Supply> supplies;

	public Book() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPublishing_house() {
		return this.publishing_house;
	}

	public void setPublishing_house(String publishing_house) {
		this.publishing_house = publishing_house;
	}

	public List<Orders> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Orders addOrder(Orders order) {
		getOrders().add(order);
		order.setBook(this);

		return order;
	}

	public Orders removeOrder(Orders order) {
		getOrders().remove(order);
		order.setBook(null);

		return order;
	}

	public List<Supply> getSupplies() {
		return this.supplies;
	}

	public void setSupplies(List<Supply> supplies) {
		this.supplies = supplies;
	}

	public Supply addSupply(Supply supply) {
		getSupplies().add(supply);
		supply.setBook(this);

		return supply;
	}

	public Supply removeSupply(Supply supply) {
		getSupplies().remove(supply);
		supply.setBook(null);

		return supply;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","Name","Author","Publishing house","Price"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, name, author, publishing_house, price};
	}

	@Override
	public void updateWith(Object mask) {
		Book obj = (Book) mask;
		this.name = obj.getName();
		this.author = obj.getAuthor();
		this.publishing_house = obj.getPublishing_house();
		this.price = obj.getPrice();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id +", "+name;
	}

	
	
}