package bookstore.services;

import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.oa.toa.TOA;
import com.sun.org.glassfish.external.statistics.BoundaryStatistic;
import com.sun.webkit.ThemeClient;

import bookstore.entities.Book;
import bookstore.entities.Cart;

/**
 * responsible for operations made on the Store, like adding and removing
 * books..
 * 
 * @author Hasan
 *
 */
public class StoreManager implements BookList {

	/**
	 * singleton instance of the Store.
	 */
	private Store store = Store.getInstance();
	
	@Override
	public Book[] list(String searchString) {
		List<Book> booksFound = new ArrayList<>();
		for (Book book : store.getAllBooks().keySet()) {
			if (book.getTitle().contains(searchString) || book.getAuthor().contains(searchString))
				booksFound.add(book);
		}
		return booksFound.toArray(new Book[0]);
	}

	@Override
	public boolean add(Book book, int quantity) {
		for (int i = 0; i < quantity; i++) {
			store.addBook(book);
		}
		return true;
	}

	/**
	 * removes one book from the bookstore.
	 * @param book
	 */
	private void remove(Book book) {
		store.removeBook(book);
	}

	@Override
	public int[] buy(Book... books) {
		int[] buyResults = new int[books.length];

		for (int i = 0; i < books.length; i++) {

			if (store.checkBookStatus(books[i]) == null) {
				buyResults[i] = 2;
			} else if (store.checkBookStatus(books[i]) == 0) {
				buyResults[i] = 1;
			} else {
				buyResults[i] = 0;
			}
			this.remove(books[i]);

		}
		return buyResults;

	}

	/**
	 * lists all books in store.
	 * @return all books in store.
	 */
	public Book[] list() {
		return store.getAllBooks().keySet().toArray(new Book[0]);
	}
	/**
	 * removes the bought books from the store.calculates the total cost.
	 * @param cart object contains books the client chose to buy.
	 * @return returns the total cost.
	 */
	public String checkOut(Cart cart) {
		List<Book> cartContents = cart.getCartContents();
		for (Book book : cartContents) {
			this.remove(book);
		}

		String details = "you bought \n" + cartContents.toString() + "\nTotal price is: " + cart.getTotalPrice();
		cart.emptyCart();
		return details;
	}

}
