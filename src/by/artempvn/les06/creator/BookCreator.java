package by.artempvn.les06.creator;

import java.util.List;
import java.util.Optional;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.request.BookDataRequest;
import by.artempvn.les06.validator.BookValidator;

public class BookCreator {

	public Optional<Book> createBook(BookDataRequest bookData) {
		BookValidator validator = new BookValidator();
		Optional<Book> book = Optional.empty();
		long id = bookData.getId();
		String title = bookData.getTitle();
		List<String> authors = bookData.getAuthors();
		int numberPages = bookData.getNumberPages();
		int yearPublishing = bookData.getYearPublishing();
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
