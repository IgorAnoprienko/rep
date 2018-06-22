package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@NamedQuery(name="Orders.findAll", query="SELECT o FROM Orders o ORDER BY o.id")
public class Orders implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private boolean paid;
	
	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="`Book ID`")
	private Book book;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="`Customer ID`")
	private Customer customer;

	public Orders() {
	}

	
	
	public Orders(boolean paid, Book book, Customer customer) {
		this.date = new Date(117, 10, 06);
		this.paid = paid;
		this.book = book;
		this.customer = customer;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getPaid() {
		return this.paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","Book","Customer", "Paid","Date"};
	}

	@Override
	public Object[] getTableRowData() {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String s = myFormat.format(date);
		String p;
		if(paid==true)p="&#10004";
		else p="&#10006";
		return new Object[]{id, book.getName(), customer.getName(), p, s};
	}

	@Override
	public void updateWith(Object mask) {
		Orders obj = (Orders) mask;
		book = obj.getBook();
		customer=obj.getCustomer();
		paid = obj.getPaid();
		date = obj.getDate();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (paid != other.paid)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return id + ","+  book.getName();
	}

	
	
}