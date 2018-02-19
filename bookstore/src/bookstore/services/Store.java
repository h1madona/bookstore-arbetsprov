package bookstore.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.TreeMap;

import bookstore.entities.Book;


/**
 *  contains informations about books in store, keeps updating upon adding and removing books.
 *  contructor is private, singleton pattern is used to obtain an instance of this class.
 * @author Hasan
 */
public class Store {
	private static volatile  Store instance;
	private static final String URL_STRING = "https://raw.githubusercontent.com/contribe/contribe/dev/bookstoredata/bookstoredata.txt";
	
	private Store() {
		initiateStore();
	}
	
	/**
	 * initiates the store with using a connection to the given url that contains books data.
	 */
	public void initiateStore() {
		URLConnection urlConnection = null;
		BufferedReader in = null;
		URL dataUrl = null;
		try {
			dataUrl = new URL(URL_STRING);
			urlConnection = dataUrl.openConnection();
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				String[] splittedLine = inputLine.split(";");
				// an array of 4 elements, the fourth element
				// the first three elements are the properties of the book.
				// last element is the quantity.
				Book book = new Book();
				book.setTitle(splittedLine[0]);
				book.setAuthor(splittedLine[1]);
				BigDecimal decimalPrice = new BigDecimal(splittedLine[2].replaceAll(",", ""));
				book.setPrice(decimalPrice);
				for(int i=0; i<Integer.parseInt(splittedLine[3]); i++)
					this.addBook(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(!in.equals(null)) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * applying singleton and double-checked locking to return an instance of the Store class.
	 * @return an instance of the {@link Store} class.
	 */
	public static Store getInstance() {
		if(instance == null) {
			synchronized (Store.class) {
				if(instance == null)
					instance = new Store();
			}
		}
		return instance;
	}
	// a map to hold books and the quantity of each book in the store.
	private Map<Book, Integer> books = new TreeMap<>();
	
	
	/**
	 * adds a book to the store. if the book is added before, just increase the quantity by 1.
	 * @param book the book to be added.
	 * @return {@link Integer} the new amount of the book in the store.
	 */
	public Integer addBook(Book book) {
		return books.merge(book, 1, (oldV, newV) -> oldV+newV);
	}

	/**
	 * decrease the amount by one only if the book exists in the store, or delete the book if the quantity reaches zero.
	 * @param book the book to be removed.
	 */
	public void removeBook(Book book) {
		books.computeIfPresent(book, (k, v) -> {
			if(v == 0) return null;
			else return --v;
		});
	}
	
	/**
	 * gets all books in the store as a map of each book with the quantity of that book in the store. 
	 * @return {@link Map} all books currently in the store.
	 */
	public Map<Book, Integer> getAllBooks(){
		return this.books;
	}
	
	/**
	 * checks the quantity of a certain book in store, or null if none exists.
	 * @param book the book to check how many items of it in the store if any.
	 * @return {@link Integer} representing the amount of a certain book is in store currently.
	 */
	public Integer checkBookStatus(Book book) {
		return books.get(book);
	}
}
