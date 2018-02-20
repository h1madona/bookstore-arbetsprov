package bookstore.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import bookstore.services.StoreManager;

public class BookTest {
	StoreManager storeManager;
	Book testBookA;
	Book testBookB;
	
	@Before
	public void initialize() {
		storeManager = new StoreManager();
		testBookA = new Book();
		testBookA.setTitle("testTitleA");
		testBookA.setAuthor("testAuthorA");
		testBookA.setPrice(BigDecimal.ONE);
		
		testBookB = new Book();
		testBookB.setTitle("testTitleB");
		testBookB.setAuthor("testAuthorB");
		testBookB.setPrice(BigDecimal.ONE);
	}

	@Test
	public void testEqualsIfTwoBooksHaveSameTitleAuthorPrice() {
		Book anotherBook = new Book();
		anotherBook.setTitle("testTitleA");
		anotherBook.setAuthor("testAuthorA");
		anotherBook.setPrice(BigDecimal.ONE);
		
		assertTrue(testBookA.equals(anotherBook));
	}

	@Test
	public void testCompareTo() {
		
		int actualCompareResult = testBookA.compareTo(testBookB);
		int expectedCompareResult = -1;//this means that the comparing instance(testBookA) should come before the compared instance(testBookB).
		
		assertEquals(expectedCompareResult, actualCompareResult);
		
	
	}

}
