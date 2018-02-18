package bookstore.services;

import bookstore.entities.Book;

public interface BookList {
	
	
	 Book[] list(String searchString);

	 boolean add(Book book, int quantity);

	 int[] buy(Book... books);
	
}
