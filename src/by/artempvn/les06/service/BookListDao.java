package by.artempvn.les06.service;

import java.util.List;

import by.artempvn.les06.entity.Book;
import by.artempvn.les06.exception.CustomException;

public interface BookListDao {

	void addBook() throws CustomException;

	void removeBook() throws CustomException;

	List<Book> findByTag() throws CustomException;

	List<Book> sortBooksByTag() throws CustomException;

}
