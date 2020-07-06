package by.artempvn.les06.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import by.artempvn.les06.creator.BookFactory;
import by.artempvn.les06.exception.CustomException;
import by.artempvn.les06.reader.CustomReader;

public class BookWarehouse {

	private final static BookWarehouse BOOK_WAREHOUSE = new BookWarehouse();
	private final static String DEFAULT_PASS_FACTORY = "input/data.txt";
	private List<Book> books;

	private BookWarehouse() {
		books = new ArrayList<>();
		BookFactory bookFactory = new BookFactory();
		try {
			books.addAll(bookFactory.createBooks(
					new CustomReader().readFile(DEFAULT_PASS_FACTORY)));
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	public static BookWarehouse getInstance() {
		return BOOK_WAREHOUSE;
	}

	public List<Book> getBooksReadOnly() {
		return Collections.unmodifiableList(books);
	}
}
