package by.artempvn.les06.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.model.dao.BookListDao;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;

public class BookListDaoImpl implements BookListDao {

	private static BookListDaoImpl daoImpl;

	private BookListDaoImpl() {
	}

	public static BookListDaoImpl getInstance() {
		if (daoImpl == null) {
			daoImpl = new BookListDaoImpl();
		}
		return daoImpl;
	}

	@Override
	public void add(Book book) throws DaoException {
		Library.getInstance().add(book);
	}

	@Override
	public void remove(Book book) throws DaoException {
		Library.getInstance().remove(book);
	}

	@Override
	public List<Book> findById(long id) {
		List<Book> books = Library.getInstance().getBooksReadOnly();
		List<Book> searchedBook = new ArrayList<>();
		for (Book book : books) {
			if (book.getId() == id) {
				searchedBook.add(book);
			}
		}
		return searchedBook;
	}

	@Override
	public List<Book> findByTitle(String title) {
		List<Book> books = Library.getInstance().getBooksReadOnly();
		List<Book> searchedBooks = new ArrayList<>();
		for (Book book : books) {
			if (book.getTitle().contains(title)) {
				searchedBooks.add(book);
			}
		}
		return searchedBooks;
	}

	@Override
	public List<Book> findByAuthor(String searchedAuthor) {
		List<Book> books = Library.getInstance().getBooksReadOnly();
		List<Book> searchedBooks = new ArrayList<>();
		outer: for (Book book : books) {
			for (String author : book.getAuthorsReadOnly()) {
				if (author.contains(searchedAuthor)) {
					searchedBooks.add(book);
					continue outer;
				}
			}
		}
		return searchedBooks;
	}

	@Override
	public List<Book> findByNumberPages(int numberPages) {
		List<Book> books = Library.getInstance().getBooksReadOnly();
		List<Book> searchedBooks = new ArrayList<>();
		for (Book book : books) {
			if (book.getNumberPages() == numberPages) {
				searchedBooks.add(book);
			}
		}
		return searchedBooks;
	}

	@Override
	public List<Book> findByYearPublishing(int yearPublishing) {
		List<Book> books = Library.getInstance().getBooksReadOnly();
		List<Book> searchedBooks = new ArrayList<>();
		for (Book book : books) {
			if (book.getYearPublishing() == yearPublishing) {
				searchedBooks.add(book);
			}
		}
		return searchedBooks;
	}

	@Override
	public List<Book> sortById() {
		List<Book> books = Library.getInstance().getBooksReadOnly();
		List<Book> sortedBooks = new ArrayList<>();
		sortedBooks.addAll(books);
		sortedBooks.sort(new Book.IdComparator());
		return sortedBooks;
	}

	@Override
	public List<Book> sortByTitle() {
		List<Book> books = Library.getInstance().getBooksReadOnly();
		List<Book> sortedBooks = new ArrayList<>();
		sortedBooks.addAll(books);
		sortedBooks.sort(new Book.TitleComparator());
		return sortedBooks;
	}

	@Override
	public List<Book> sortByAuthor() {
		List<Book> books = Library.getInstance().getBooksReadOnly();
		List<Book> sortedBooks = new ArrayList<>();
		sortedBooks.addAll(books);
		sortedBooks.sort(new Book.AuthorComparator());
		return sortedBooks;
	}

	@Override
	public List<Book> sortByNumberPages() {
		List<Book> books = Library.getInstance().getBooksReadOnly();
		List<Book> sortedBooks = new ArrayList<>();
		sortedBooks.addAll(books);
		sortedBooks.sort(new Book.NumberPagesComparator());
		return sortedBooks;
	}

	@Override
	public List<Book> sortByYearPublishing() {
		List<Book> books = Library.getInstance().getBooksReadOnly();
		List<Book> sortedBooks = new ArrayList<>();
		sortedBooks.addAll(books);
		sortedBooks.sort(new Book.YearPublishingComparator());
		return sortedBooks;
	}

	@Override
	public List<Book> takeAllBooks() {
		return Library.getInstance().getBooksReadOnly();
	}
}
