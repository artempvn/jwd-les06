package by.artempvn.les06.model.service;

import java.util.List;
import java.util.Optional;
import by.artempvn.les06.model.dao.impl.BookListDaoImpl;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.exception.CustomException;
import by.artempvn.les06.model.validator.BookValidator;

public class BookListDaoService {

	public boolean addBook(long id, String title, List<String> authors,
			int numberPages, int yearPublishing) throws CustomException {
		Optional<Book> book = createBook(id, title, authors, numberPages,
				yearPublishing);
		boolean isAdded = true;
		if (book.isPresent()) {
			try {
				new BookListDaoImpl().addBook(book.get());
			} catch (CustomException e) {
				isAdded = false;
			}
		} else {
			throw new CustomException("Invalid data input");
		}
		return isAdded;
	}

	public boolean removeBook(long id, String title, List<String> authors,
			int numberPages, int yearPublishing) throws CustomException {
		Optional<Book> book = createBook(id, title, authors, numberPages,
				yearPublishing);
		boolean isRemoved = true;
		if (book.isPresent()) {
			try {
				new BookListDaoImpl().removeBook(book.get());
			} catch (CustomException e) {
				isRemoved = false;
			}
		} else {
			throw new CustomException("Invalid data input");
		}
		return isRemoved;
	}

	private Optional<Book> createBook(long id, String title,
			List<String> authors, int numberPages, int yearPublishing) {
		BookValidator validator = new BookValidator();
		Optional<Book> book = Optional.empty();
		boolean correctInput = (validator.isIdCorrect(id)
				&& validator.isTitleCorrect(title)
				&& validator.isAuthorsCorrect(authors)
				&& validator.isNumberPagesCorrect(numberPages)
				&& validator.isYearPublishingCorrect(yearPublishing));
		if (correctInput) {
			book = Optional.of(
					new Book(id, title, authors, numberPages, yearPublishing));
		}
		return book;
	}
}
