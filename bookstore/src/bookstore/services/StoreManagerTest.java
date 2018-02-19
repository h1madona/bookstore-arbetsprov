package bookstore.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import bookstore.entities.Book;

public class StoreManagerTest {
	StoreManager storeManager ;
	Book book;
	@Before
	public void initialize() {
		storeManager = new StoreManager();
		book = new Book();
		book.setTitle("testTitle");
		book.setAuthor("testAuthor");
		book.setPrice(new BigDecimal(111));
	
		
	}

	@Test
	public void testListSearchByTitle() {
		storeManager.add(book, 3);
		Book[] returnedBooks = storeManager.list(book.getTitle());
		Book actual = returnedBooks[0];
		Book expected = book;
		assertEquals(expected, actual);
	}
	@Test
	public void testListSearchByAuthor() {
		storeManager.add(book, 3);
		Book[] returnedBooks = storeManager.list(book.getAuthor());
		Book actual = returnedBooks[0];
		Book expected = book;
		assertEquals(expected, actual);
	}



	@Test
	public void testBuyNoneExistingBook() {
		Book book = new Book();
		book.setTitle("tale of non-existing book!");
		book.setAuthor("any author");
		book.setPrice(BigDecimal.ONE);
		int[] returnedBuyResults = storeManager.buy(book);
		int expected = 2;
		int actual = returnedBuyResults[0];
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuyExistingBook() {
		storeManager.add(book, 3);
		int[] returnedBuyResults = storeManager.buy(book);
		int expected = 0;//indicated the success of buy operation.
		int actual = returnedBuyResults[0];
		assertEquals(expected, actual);
		
		//check if the book is removed after buying it.
		int actualBookQuantity = Store.getInstance().checkBookStatus(book);
		int expectedQuantity = 2; //after buying 1 book, should be 2 books left out of 3 added initially.
		assertEquals(expectedQuantity, actualBookQuantity);
	}





}
