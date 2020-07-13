package by.artempvn.les06.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import by.artempvn.les06.model.exception.ModelException;
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

	public void addBook(Book book) throws ModelException {
		if (book == null) {
			throw new ModelException("Null input");
		}
		if (books.contains(book)) {
			throw new ModelException("Book is already added");
		} else {
			if (!isIdUnique(book)) {
				throw new ModelException("ID is already used");
			}
			books.add(book);
			giveId(book);
		}
	}

	public void removeBook(Book book) throws ModelException {
		if (book == null) {
			throw new ModelException("Null input");
		}
		if (books.contains(book)) {
			books.remove(book);
		} else {
			throw new ModelException("There is no such book");
		}
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
