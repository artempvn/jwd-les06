package by.artempvn.les06.controller.command.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import by.artempvn.les06.controller.command.Command;
import by.artempvn.les06.controller.command.FieldType;
import by.artempvn.les06.exception.ControllerException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.service.BookService;
import by.artempvn.les06.validator.InputDataValidator;

public class SortByTagCommand implements Command {
	private static final String KEY_VALUE = "Updated list";
	private static final String SORT_PARAM = "tag";
	private BookService bookService;

	@Override
	public Map<String, List<Book>> execute(Map<String, String> params)
			throws ControllerException {
		bookService = BookService.getInstance();
		InputDataValidator dataValidator = new InputDataValidator();
		if (!dataValidator.isCorrectFieldData(params.get(SORT_PARAM))) {
			throw new ControllerException("Invalid field data");
		}
		FieldType sortingType = FieldType
				.valueOf(params.get(SORT_PARAM).toUpperCase());
		List<Book> sortedList = null;
		switch (sortingType) {
		case ID:
			sortedList = bookService.sortById();
			break;
		case TITLE:
			sortedList = bookService.sortByTitle();
			break;
		case AUTHORS:
			sortedList = bookService.sortByAuthor();
			break;
		case NUMBER_PAGES:
			sortedList = bookService.sortByNumberPages();
			break;
		case YEAR_PUBLISHING:
			sortedList = bookService.sortByNumberPages();
			break;
		}
		Map<String, List<Book>> output = new HashMap<>();
		output.put(KEY_VALUE, sortedList);
		return output;
	}
}
