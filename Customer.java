package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c ORDER BY c.name")
public class Customer implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private String name;

	private String phone;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="customer")
	private List<Orders> orders;

	
	
	public Customer(String address, String name, String phone) {
		this.address = address;
		this.name = name;
		this.phone = phone;
	}

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Orders> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Orders addOrder(Orders order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Orders removeOrder(Orders order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","Name","Address","Phone"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[]{id, name, address, phone};
	}

	@Override
	public void updateWith(Object mask) {
		Customer obj = (Customer) mask;
		name = obj.getName();
		address = obj.getAddress();
		phone = obj.getPhone();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + ", " + name;
	}

	
	
}