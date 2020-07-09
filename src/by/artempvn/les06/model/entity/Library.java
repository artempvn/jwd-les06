package by.artempvn.les06.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.artempvn.les06.creator.BookCreator;
import by.artempvn.les06.model.exception.CustomException;
import by.artempvn.les06.model.util.CustomId;
import by.artempvn.les06.reader.CustomReader;

public class Library {

	private static Library library;
	private final static String DEFAULT_PASS_FACTORY = "input/data.txt";
	private List<Book> books;

	private Library() {
		books = new ArrayList<>();
		BookCreator bookFactory = new BookCreator();
		try {
			books.addAll(bookFactory.createBooks(
					new CustomReader().readFile(DEFAULT_PASS_FACTORY)));
		} catch (CustomException e) {
			// place for log in future
		}
		for(Book book: books) {
			giveId(book);
		}
	}

	public static Library getInstance() {
		if (library==null) {
			library= new Library();
		}
		return library;
	}

	public List<Book> getBooksReadOnly() {
		return Collections.unmodifiableList(books);
	}

	public void addBook(Book book) throws CustomException {
		if (book==null) {
			throw new CustomException("Null input");
		}
		if (books.contains(book)) {
			throw new CustomException("Book is already added");
		} else {
			if (!isIdUnique(book)) {
					throw new CustomException("ID is already used");
			}
			books.add(book);
			giveId(book);
		}
	}

	public void removeBook(Book book) throws CustomException {
		if (book==null) {
			throw new CustomException("Null input");
		}
		if (books.contains(book)) {
			books.remove(book);
		} else {
			throw new CustomException("There is no such book");
		}
	}
	
	private void giveId (Book book) {
		if(book.getId()==-1) {
			book.setId(CustomId.takeId());
		}
	}
	
	private boolean isIdUnique(Book book) {
		boolean isUnique=true;
		for (Book bookInLibrary : books) {
			if (bookInLibrary.getId()==book.getId()) {
				isUnique=false;
			}
		}
		return isUnique;
	}

}
