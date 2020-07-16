package by.artempvn.les06.controller.command.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import by.artempvn.les06.controller.command.Command;
import by.artempvn.les06.controller.command.FieldType;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.service.BookService;
import by.artempvn.les06.validator.InputDataValidator;

public class FindByTagCommand implements Command {
	private static final String KEY_VALUE = "Updated list";
	private static final String SEARCH_PARAM = "tag";
	private static final String VALUE_PARAM = "value";
	private BookService bookService;

	@Override
	public Map<String, List<Book>> execute(Map<String, String> params) {
		bookService = BookService.getInstance();
		InputDataValidator dataValidator = new InputDataValidator();
		List<Book> foundList = Collections.emptyList();
		if (dataValidator.isCorrectFieldData(params.get(SEARCH_PARAM))) {
			FieldType searchType = FieldType
					.valueOf(params.get(SEARCH_PARAM).toUpperCase());
			String value = params.get(VALUE_PARAM);
			switch (searchType) {
			case ID:
				if (dataValidator.isLong(value)) {
					foundList = bookService.findById(Long.parseLong(value));
				}
				break;
			case TITLE:
				foundList = bookService.findByTitle(value);
				break;
			case AUTHORS:
				foundList = bookService.findByAuthor(value);
				break;
			case NUMBER_PAGES:
				if (dataValidator.isNumber(value)) {
					foundList = bookService
							.findByNumberPages(Integer.parseInt(value));
				}
				break;
			case YEAR_PUBLISHING:
				if (dataValidator.isNumber(value)) {
					foundList = bookService
							.findByYearPublishing(Integer.parseInt(value));
				}
				break;
			}
		} else {
			foundList = bookService.takeAllBooks();
		}
		Map<String, List<Book>> output = new HashMap<>();
		output.put(KEY_VALUE, foundList);
		return output;
	}
}
