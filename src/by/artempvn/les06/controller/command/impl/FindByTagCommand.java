package by.artempvn.les06.controller.command.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import by.artempvn.les06.controller.command.Command;
import by.artempvn.les06.controller.command.FieldType;
import by.artempvn.les06.controller.exception.ControllerException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.service.BookService;
import by.artempvn.les06.validator.InputDataValidator;

public class FindByTagCommand implements Command {
	private static final String SEARCH_PARAM = "tag";
	private BookService bookService;

	@Override
	public Map<String, Object> execute(Map<String, String> params)
			throws ControllerException {
		bookService = new BookService();
		InputDataValidator dataValidator = new InputDataValidator();
		List<Book> foundList = null;
		if (dataValidator.isCorrectFieldData(params.get(SEARCH_PARAM))) {
			FieldType searchType = FieldType
					.valueOf(params.get(SEARCH_PARAM).toUpperCase());
			switch (searchType) {
			case ID:
				foundList = bookService.sortById();
				break;
			case TITLE:
				foundList = bookService.sortByTitle();
				break;
			case AUTHORS:
				foundList = bookService.sortByAuthor();
				break;
			case NUMBER_PAGES:
				foundList = bookService.sortByNumberPages();
				break;
			case YEAR_PUBLISHING:
				foundList = bookService.sortByNumberPages();
				break;
			}
		} else {
			foundList = bookService.takeAllBooks();
		}
		Map<String, Object> output = new HashMap<>();
		output.put("found list", foundList);
		return output;
	}
}
