package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the provider database table.
 * 
 */
@Entity
@NamedQuery(name="Provider.findAll", query="SELECT p FROM Provider p ORDER BY p.id")
public class Provider implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private String city;

	private String name;

	private String phone;

	//bi-directional many-to-one association to Supply
	@OneToMany(mappedBy="provider")
	private List<Supply> supplies;

	
	
	public Provider(String address, String city, String name, String phone) {
		this.address = address;
		this.city = city;
		this.name = name;
		this.phone = phone;
	}

	public Provider() {
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

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public List<Supply> getSupplies() {
		return this.supplies;
	}

	public void setSupplies(List<Supply> supplies) {
		this.supplies = supplies;
	}

	public Supply addSupply(Supply supply) {
		getSupplies().add(supply);
		supply.setProvider(this);

		return supply;
	}

	public Supply removeSupply(Supply supply) {
		getSupplies().remove(supply);
		supply.setProvider(null);

		return supply;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","Name","Address","City","Phone"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[]{id, name, address, city, phone};
	}

	@Override
	public void updateWith(Object mask) {
		Provider obj = (Provider) mask;
		name = obj.getName();
		address = obj.getAddress();
		city = obj.getCity();
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
		Provider other = (Provider) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
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