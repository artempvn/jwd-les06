package by.artempvn.les06.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import by.artempvn.les06.creator.BookCreator;
import by.artempvn.les06.model.dao.impl.BookListDaoImpl;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.exception.ModelException;
import by.artempvn.les06.model.exception.ServiceException;
import by.artempvn.les06.validator.BookValidator;

public class BookService {

	public boolean addBook(Map<String, Object> bookData) throws ModelException {
		BookCreator bookCreator=new BookCreator();
		Optional<Book> book = bookCreator.createBook(bookData);
		boolean isAdded = true;
		if (book.isPresent()) {
			try {
				new BookListDaoImpl().addBook(book.get());
			} catch (ModelException e) {
				isAdded = false;
			}
		} else {
			throw new ModelException("Invalid data input");
		}
		return isAdded;
	}

	public boolean removeBook(Map<String, Object> bookData) throws ModelException {
		BookCreator bookCreator=new BookCreator();
		Optional<Book> book = bookCreator.createBook(bookData);
		boolean isRemoved = true;
		if (book.isPresent()) {
			try {
				new BookListDaoImpl().removeBook(book.get());
			} catch (ModelException e) {
				isRemoved = false;
			}
		} else {
			throw new ModelException("Invalid data input");
		}
		return isRemoved;
	}
	
	public List<Book> findById(long id) throws ServiceException  {
		BookValidator validator=new BookValidator();
		if (!validator.isIdCorrect(id)) {
			throw new ServiceException("Incorrect input data");
		}
		return (new BookListDaoImpl().findById(id));
	}

	public List<Book> findByTitle(String title) throws ServiceException  {
		BookValidator validator=new BookValidator();
		if (!validator.isTitleCorrect(title)) {
			throw new ServiceException("Incorrect input data");
		}
		return (new BookListDaoImpl().findByTitle(title));
	}

	public List<Book> findByAuthor(String author) throws ServiceException {
		BookValidator validator=new BookValidator();
		if (!validator.isAuthorCorrect(author)) {
			throw new ServiceException("Incorrect input data");
		}
		return (new BookListDaoImpl().findByAuthor(author));
	}

	public List<Book> findByNumberPages(int numberPages) throws ServiceException {
		BookValidator validator=new BookValidator();
		if (!validator.isNumberPagesCorrect(numberPages)) {
			throw new ServiceException("Incorrect input data");
		}
		return (new BookListDaoImpl().findByNumberPages(numberPages));
	}
	
	public List<Book> findByYearPublishing(int yearPublishing) throws ServiceException {
		BookValidator validator=new BookValidator();
		if (!validator.isYearPublishingCorrect(yearPublishing)) {
			throw new ServiceException("Incorrect input data");
		}
		return (new BookListDaoImpl().findByYearPublishing(yearPublishing));
	}

	public List<Book> sortById() {
		return (new BookListDaoImpl().sortById());
	}

	public List<Book> sortByTitle() {
		return (new BookListDaoImpl().sortByTitle());
	}

	public List<Book> sortByAuthor(){
		return (new BookListDaoImpl().sortByAuthor());
	}

	public List<Book> sortByNumberPages() {
		return (new BookListDaoImpl().sortByNumberPages());
	}

	public List<Book> sortByYearPublishing(){
		return (new BookListDaoImpl().sortByYearPublishing());
	}
	
	public List<Book> takeAllBooks() {
		return (new BookListDaoImpl().takeAllBooks());
	}
	
}
