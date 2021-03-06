package by.artempvn.les06.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.util.CustomId;

public class Library {

	private static Library library;
	private List<Book> books;

	private Library() {
		books = new ArrayList<>();
	}

	public static Library getInstance() {
		if (library == null) {
			library = new Library();
		}
		return library;
	}

	public List<Book> getBooksReadOnly() {
		return Collections.unmodifiableList(books);
	}

	public void add(Book book) throws DaoException {
		if (book != null) {
			if (books.contains(book)) {
				throw new DaoException("Book is already added");
			} else {
				if (!isIdUnique(book)) {
					throw new DaoException("ID is already used");
				}
				books.add(book);
				giveId(book);
			}
		}	
	}

	public void remove(Book book) throws DaoException {
		if (book != null) {
			if (books.contains(book)) {
				books.remove(book);
			} else {
				throw new DaoException("There is no such book");
			}
		}
	}
	
	public void removeAll() {
		books = new ArrayList<>();
	}

	private void giveId(Book book) {
		if (book.getId() == -1) {
			book.setId(CustomId.takeId());
		}
	}

	private boolean isIdUnique(Book book) {
		boolean isUnique = true;
		for (Book bookInLibrary : books) {
			if (bookInLibrary.getId() == book.getId()) {
				isUnique = false;
			}
		}
		return isUnique;
	}
}
