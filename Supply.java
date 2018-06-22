package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the supply database table.
 * 
 */
@Entity
@NamedQuery(name="Supply.findAll", query="SELECT s FROM Supply s ORDER BY s.id")
public class Supply implements Serializable, IModel{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private int count;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="`Book ID`")
	private Book book;

	//bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(name="`Provider ID`")
	private Provider provider;

	public Supply() {
	}

	
	
	public Supply(int count,  Book book, Provider provider) {
		
		this.count = count;
		this.date = new Date(117, 10, 06);
		this.book = book;
		this.provider = provider;
	
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","Book","Count","Provider","Date"};
	}

	@Override
	public Object[] getTableRowData() {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String s = myFormat.format(date);
		return new Object[]{id, book.getName(), count, provider.getName(), s};
	}

	@Override
	public void updateWith(Object mask) {
		Supply obj = (Supply) mask;
		book = obj.getBook();
		provider = obj.getProvider();
		count = obj.getCount();
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
		Supply other = (Supply) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (count != other.count)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return id + ","+  book.getName();
	}

}