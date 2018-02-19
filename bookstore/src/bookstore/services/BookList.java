package bookstore.services;

import java.util.PrimitiveIterator.OfDouble;

import bookstore.entities.Book;

/**
 * @author Hasan
 *
 */
public interface BookList {
	
	
	/**
	 * lists books bases on a search criteria.
	 * @param searchString
	 * @return array of found books.
	 */
	 Book[] list(String searchString);

	 
	 /**
	 * @param book to be added to the store.
	 * @param quantity of books.
	 * @return {@link Boolean} wether the book has been added successfully or not.
	 */
	boolean add(Book book, int quantity);
	
	
	
	 /**
	 * confirms the buying of the books passed in.
	 * @param books that the client chose to buy.
	 * @return int[] contins the result of the buying operations on each book, represented as follows: ok(0), notInStock(1), doesNotExist(2).
	 */
	int[] buy(Book... books);
	
}
