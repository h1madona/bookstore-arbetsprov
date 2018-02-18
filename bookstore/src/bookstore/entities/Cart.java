package bookstore.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xpath.internal.axes.OneStepIterator;

import bookstore.services.Store;
import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;

/**
 * @author Hasan
 * holds cart information, allowing a client to manipulate contents by adding, removing....etc
 */
public class Cart {
	
	
	private List<Book> booksInCart = new ArrayList<>();
	private Store store = Store.getInstance();

	/**
	 * lists all books currently in the cart.
	 */
	public List<Book> getCartContents() {
		return booksInCart;
	}

	
	/**
	 * @param books
	 * adds single, or multiple books to the cart.
	 * @return an array of the results of the adding operations.represented by the numbers 0: added, 1: not in stock, 2: does not exist.
	 */
	public int[] addToCart(Book... books) {
		
		int[] addingResults = new int[books.length];
		for (int i=0; i<books.length; i++) {
			if(store.checkBookStatus(books[i]) == null) {
				addingResults[i] = 2;
			}
			else if(store.checkBookStatus(books[i]) == 0 ) {
					addingResults[i] = 1;
			}else {
				addingResults[i] = 0;
				booksInCart.add(books[i]);
			}
			
		}return addingResults;

	}
	/**
	 * removes one book from the cart.
	 * @param book
	 */
	public void removeFromCart(Book book) {
		this.booksInCart.remove(book);
	}
	
	/**
	 * emptys the cart contents.
	 */
	public void emptyCart() {
		this.booksInCart.clear();
	}

	/**
	 * calculates the total price of the books in the cart.
	 * @return bigdecimal number representing the total price.
	 */
	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = new BigDecimal(0);
		for (int i = 0; i < booksInCart.size(); i++) {
			totalPrice = totalPrice.add(booksInCart.get(i).getPrice());
		}
		return totalPrice;
	}

}
