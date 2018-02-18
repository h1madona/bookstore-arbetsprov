package bookstore.services;

import java.util.ArrayList;
import java.util.List;

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

	public Book[] list() {
		return store.getAllBooks().keySet().toArray(new Book[0]);
	}

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
