package by.artempvn.les06.creator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.exception.CustomException;

public class BookCreator {
	private final static String FIELD_DELIMITER = "//";
	private final static String AUTHOR_DELIMITER = "/";

	public List<Book> createBooks(List<String> booksData)
			throws CustomException {
		List<Book> books = new ArrayList<>();
		for (String bookData : booksData) {
			String[] fields = bookData.split(FIELD_DELIMITER);
			String title = fields[0];
			String[] authors = fields[1].split(AUTHOR_DELIMITER);
			int numberPages = Integer.parseInt(fields[2]);
			int yearPublishing = Integer.parseInt(fields[3]);
			books.add(new Book(title, Arrays.asList(authors), numberPages,
					yearPublishing));
		}
		return books;
	}
}
