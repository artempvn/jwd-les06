package by.artempvn.les06.controller.command.impl;

import java.util.HashMap;
import java.util.Map;
import by.artempvn.les06.controller.command.Command;
import by.artempvn.les06.controller.exception.ControllerException;
import by.artempvn.les06.model.exception.ModelException;
import by.artempvn.les06.model.service.BookService;
import by.artempvn.les06.parser.BookMapParser;

public class AddBookCommand implements Command {
	private BookService bookService;

	@Override
	public Map<String, Object> execute(Map<String, String> params)
			throws ControllerException {
		BookMapParser bookMapParser = new BookMapParser();
		Map<String, Object> bookParams = bookMapParser.parseMapData(params);
		boolean isAdded = false;
		bookService = new BookService();
		try {
			isAdded = bookService.addBook(bookParams);
		} catch (ModelException e) {
			throw new ControllerException("Failed to add book", e);
		}
		Map<String, Object> output = new HashMap<>();
		output.put("is added", isAdded);
		return output;
	}
}
