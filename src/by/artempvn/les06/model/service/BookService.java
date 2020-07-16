package by.artempvn.les06.model.service;

import java.util.List;
import java.util.Optional;
import by.artempvn.les06.creator.BookCreator;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.exception.ServiceException;
import by.artempvn.les06.model.dao.impl.BookListDaoImpl;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.request.BookDataRequest;

public class BookService {

	private static BookService bookService;

	private BookService() {
	}

	public static BookService getInstance() {
		if (bookService == null) {
			bookService = new BookService();
		}
		return bookService;
	}

	public void add(BookDataRequest bookData) throws ServiceException {
		BookCreator bookCreator = new BookCreator();
		Optional<Book> book = bookCreator.createBook(bookData);
		if (book.isPresent()) {
			try {
				BookListDaoImpl.getInstance().add(book.get());
			} catch (DaoException e) {
				throw new ServiceException("Failed to add book");
			}
		} else {
			throw new ServiceException("Invalid data input");
		}
	}

	public void remove(BookDataRequest bookData) throws ServiceException {
		BookCreator bookCreator = new BookCreator();
		Optional<Book> book = bookCreator.createBook(bookData);
		if (book.isPresent()) {
			try {
				BookListDaoImpl.getInstance().remove(book.get());
			} catch (DaoException e) {
				throw new ServiceException("Failed to remove book");
			}
		} else {
			throw new ServiceException("Invalid data input");
		}
	}

	public List<Book> findById(long id) {
		List<Book> searchedBook = BookListDaoImpl.getInstance().findById(id);
		return searchedBook;
	}

	public List<Book> findByTitle(String title) {
		List<Book> searchedBooks = BookListDaoImpl.getInstance()
				.findByTitle(title);
		return searchedBooks;
	}

	public List<Book> findByAuthor(String author) {
		List<Book> searchedBooks = BookListDaoImpl.getInstance()
				.findByAuthor(author);
		return searchedBooks;
	}

	public List<Book> findByNumberPages(int numberPages) {
		List<Book> searchedBooks = BookListDaoImpl.getInstance()
				.findByNumberPages(numberPages);
		return searchedBooks;
	}

	public List<Book> findByYearPublishing(int yearPublishing) {
		List<Book> searchedBooks = BookListDaoImpl.getInstance()
				.findByYearPublishing(yearPublishing);
		return searchedBooks;
	}

	public List<Book> sortById() {
		List<Book> sortedBooks = BookListDaoImpl.getInstance().sortById();
		return sortedBooks;
	}

	public List<Book> sortByTitle() {
		List<Book> sortedBooks = BookListDaoImpl.getInstance().sortByTitle();
		return sortedBooks;
	}

	public List<Book> sortByAuthor() {
		List<Book> sortedBooks = BookListDaoImpl.getInstance().sortByAuthor();
		return sortedBooks;
	}

	public List<Book> sortByNumberPages() {
		List<Book> sortedBooks = BookListDaoImpl.getInstance()
				.sortByNumberPages();
		return sortedBooks;
	}

	public List<Book> sortByYearPublishing() {
		List<Book> sortedBooks = BookListDaoImpl.getInstance()
				.sortByYearPublishing();
		return sortedBooks;
	}

	public List<Book> takeAllBooks() {
		List<Book> books = BookListDaoImpl.getInstance().takeAllBooks();
		return books;
	}
}
