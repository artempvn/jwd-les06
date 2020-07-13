package by.artempvn.les06.creator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import by.artempvn.les06.controller.command.FieldType;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.exception.ModelException;
import by.artempvn.les06.validator.BookValidator;

public class BookCreator {

	public Optional<Book> createBook(Map<String, Object> bookData)
			throws ModelException {
		BookValidator validator = new BookValidator();
		Optional<Book> book = Optional.empty();
		long id = (long) bookData.get(FieldType.ID.name());
		String title = (String) bookData.get(FieldType.TITLE.name());
		List<String> authors = (List<String>) bookData
				.get(FieldType.AUTHORS.name());
		int numberPages = (int) bookData.get(FieldType.NUMBER_PAGES.name());
		int yearPublishing = (int) bookData
				.get(FieldType.YEAR_PUBLISHING.name());
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
