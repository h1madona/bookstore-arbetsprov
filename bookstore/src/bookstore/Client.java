package bookstore;

import bookstore.entities.Book;
import bookstore.entities.Cart;
import bookstore.services.Store;
import bookstore.services.StoreManager;

public class Client {
	public static void main(String[] args) {

		
		
		//creating an instance of the store.
		StoreManager storeManager = new StoreManager();
		Store store = Store.getInstance();
		
 		//display all books.
		System.out.println("all books: \n");
		store.getAllBooks().forEach((b, i) -> System.out.println(b.getTitle()+", Author: "+b.getAuthor()+", Price: "+b.getPrice()+ ", amount: "+i));
		System.out.println("---------------");

		
		//add the same book to the store should increase the quantity.
		store.addBook(storeManager.list()[0]);
		//display after adding a duplicate book.
		System.out.println("all books after adding a duplicate book: \n");
		store.getAllBooks().forEach((b, i) -> System.out.println(b.getTitle()+", Author: "+b.getAuthor()+", Price: "+b.getPrice()+ ", amount: "+i));
		System.out.println("---------------");


		
		//the client can display all books, search for a certain book, and add or remove
		//books from the Cart, and finally check out to get the details of the whole operation.
		Cart cart = new Cart();
		Book[] books = storeManager.list();
		int[] buyResult = cart.addToCart(books[0], books[1], books[2]);
		String details = storeManager.checkOut(cart);
		System.out.println("---------------");
		System.out.println(details);
		
	}
}
