package bookstore.entities;

import java.math.BigDecimal;
import java.util.TreeMap;

/**
 * @author Hasan
 * Entity class to hold information about one book.
 * implements {@link Comparable} to support using of {@link TreeMap}.
 */
public class Book implements Comparable<Book>{
	
	
	private String title;
	private String author;
	private BigDecimal price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public BigDecimal getPrice() {
		return price;
	}
	
	
	/**
	 * @param price
	 * sets the price of the book.
	 * throws {@link IllegalStateException} if the price is a negative number.
	 */
	public void setPrice(BigDecimal price) {
		if(price.intValue() < 0) throw new IllegalStateException("price can't be less than zero");
		this.price = price;
	}
	
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return title + ", author=" + author + ", price=" + price;
	}
	@Override
	public int compareTo(Book book) {
		int compareByTitle = this.title.compareTo(book.getTitle());
		int compareByAuthor = this.author.compareTo(book.getAuthor());
		int compareByPrice = this.price.compareTo(book.getPrice());
		
		if(compareByTitle != 0) return compareByTitle;
		return compareByAuthor!=0?compareByAuthor:compareByPrice;
	
	}
	
	
	
}
