package by.artempvn.les06.model.dao;

import java.util.List;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.exception.ModelException;

public interface BookListDao {

	void addBook(Book book) throws ModelException;

	void removeBook(Book book) throws ModelException;

	List<Book> findById(long id);
	
	List<Book> findByTitle(String title);
	
	List<Book> findByAuthor(String author);
	
	List<Book> findByNumberPages(int numberPages);
	
	List<Book> findByYearPublishing(int yearPublishing);
	
	List<Book> sortById() ;
	
	List<Book> sortByTitle();
	
	List<Book> sortByAuthor();
	
	List<Book> sortByNumberPages();
	
	List<Book> sortByYearPublishing();
	
	List<Book> takeAllBooks();
}
