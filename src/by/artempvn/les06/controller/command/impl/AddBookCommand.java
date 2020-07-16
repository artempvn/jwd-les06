package by.artempvn.les06.controller.command.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import by.artempvn.les06.controller.command.Command;
import by.artempvn.les06.exception.ControllerException;
import by.artempvn.les06.exception.ServiceException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.service.BookService;
import by.artempvn.les06.parser.BookMapParser;
import by.artempvn.les06.request.BookDataRequest;

public class AddBookCommand implements Command {
	private static final String KEY_VALUE = "Updated list";
	private BookService bookService;

	@Override
	public Map<String, List<Book>> execute(Map<String, String> params)
			throws ControllerException {
		BookMapParser bookMapParser = new BookMapParser();
		BookDataRequest bookData = bookMapParser.parseMapData(params);
		bookService = BookService.getInstance();
		try {
			bookService.add(bookData);
		} catch (ServiceException e) {
			throw new ControllerException("Failed to add book", e);
		}
		Map<String, List<Book>> output = new HashMap<>();
		output.put(KEY_VALUE, BookService.getInstance().takeAllBooks());
		return output;
	}
}
