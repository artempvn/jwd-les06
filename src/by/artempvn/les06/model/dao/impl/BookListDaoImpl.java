package by.artempvn.les06.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import by.artempvn.les06.model.dao.BookListDao;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;
import by.artempvn.les06.model.exception.CustomException;

public class BookListDaoImpl implements BookListDao {

	@Override
	public void addBook(Book book) throws CustomException {
		Library.getInstance().addBook(book);	
	}

	@Override
	public void removeBook(Book book) throws CustomException {
		Library.getInstance().removeBook(book);
	}

	@Override
	public List<Book> findById(long id)  {
		List<Book> books=Library.getInstance().getBooksReadOnly();
		List<Book> searchedBook= new ArrayList<>();
		for (Book book:books) {
			if (book.getId()==id) {
				searchedBook.add(book);
			}
		}
		return searchedBook;
	}

	@Override
	public List<Book> findByTitle(String title)  {
		List<Book> books=Library.getInstance().getBooksReadOnly();
		List<Book> searchedBooks= new ArrayList<>();
		for (Book book:books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				searchedBooks.add(book);
			}
		}
		return searchedBooks;
	}

	@Override
	public List<Book> findByAuthor(List<String> authors) {
		List<Book> books=Library.getInstance().getBooksReadOnly();
		List<Book> searchedBooks= new ArrayList<>();
		for (Book book:books) {
			if (book.getAuthorsReadOnly().equals(authors)) {
				searchedBooks.add(book);
			}
		}
		return searchedBooks;
	}

	@Override
	public List<Book> findByNumberPages(int numberPages) {
		List<Book> books=Library.getInstance().getBooksReadOnly();
		List<Book> searchedBooks= new ArrayList<>();
		for (Book book:books) {
			if (book.getNumberPages()==numberPages) {
				searchedBooks.add(book);
			}
		}
		return searchedBooks;
	}

	@Override
	public List<Book> findByYearPublishing(int yearPublishing) {
		List<Book> books=Library.getInstance().getBooksReadOnly();
		List<Book> searchedBooks= new ArrayList<>();
		for (Book book:books) {
			if (book.getNumberPages()==yearPublishing) {
				searchedBooks.add(book);
			}
		}
		return searchedBooks;
	}

	@Override
	public List<Book> sortById() {
		List<Book> books=Library.getInstance().getBooksReadOnly();
		List<Book> sortedBooks= new ArrayList<>();
		sortedBooks.addAll(books);
		sortedBooks.sort(new Book.IdComparator());
		return sortedBooks;
	}

	@Override
	public List<Book> sortByTitle() {
		List<Book> books=Library.getInstance().getBooksReadOnly();
		List<Book> sortedBooks= new ArrayList<>();
		sortedBooks.addAll(books);
		sortedBooks.sort(new Book.TitleComparator());
		return sortedBooks;
	}

	@Override
	public List<Book> sortByAuthor(){
		List<Book> books=Library.getInstance().getBooksReadOnly();
		List<Book> sortedBooks= new ArrayList<>();
		sortedBooks.addAll(books);
		sortedBooks.sort(new Book.AuthorComparator());
		return sortedBooks;
	}

	@Override
	public List<Book> sortByNumberPages() {
		List<Book> books=Library.getInstance().getBooksReadOnly();
		List<Book> sortedBooks= new ArrayList<>();
		sortedBooks.addAll(books);
		sortedBooks.sort(new Book.NumberPagesComparator());
		return sortedBooks;
	}

	@Override
	public List<Book> sortByYearPublishing(){
		List<Book> books=Library.getInstance().getBooksReadOnly();
		List<Book> sortedBooks= new ArrayList<>();
		sortedBooks.addAll(books);
		sortedBooks.sort(new Book.YearPublishingComparator());
		return sortedBooks;
	}
	
	@Override
	public List<Book> takeAllBooks() {
		return Library.getInstance().getBooksReadOnly();
	}
}
