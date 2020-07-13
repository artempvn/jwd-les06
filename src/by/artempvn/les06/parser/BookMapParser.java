package by.artempvn.les06.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import by.artempvn.les06.controller.command.FieldType;
import by.artempvn.les06.controller.exception.ControllerException;
import by.artempvn.les06.validator.InputDataValidator;

public class BookMapParser {
	private static final String AUTHOR_SPLITTER = "/";

	public Map<String, Object> parseMapData(Map<String, String> params)
			throws ControllerException {
		String id = params.get(FieldType.ID.name());
		String title = params.get(FieldType.TITLE.name());
		String authors = params.get(FieldType.AUTHORS.name());
		String numberPages = params.get(FieldType.NUMBER_PAGES.name());
		String yearPublishing = params.get(FieldType.YEAR_PUBLISHING.name());
		InputDataValidator dataValidator = new InputDataValidator();
		if (!dataValidator.isLong(id) || !dataValidator.isNumber(numberPages)
				|| !dataValidator.isNumber(yearPublishing)) {
			throw new ControllerException("Invalid data input");
		}
		Map<String, Object> bookParams = new HashMap<>();
		bookParams.put(FieldType.ID.name(), Long.parseLong(id));
		bookParams.put(FieldType.TITLE.name(), title);
		bookParams.put(FieldType.AUTHORS.name(),
				Arrays.asList(authors.split(AUTHOR_SPLITTER)));
		bookParams.put(FieldType.NUMBER_PAGES.name(),
				Integer.parseInt(numberPages));
		bookParams.put(FieldType.YEAR_PUBLISHING.name(),
				Integer.parseInt(yearPublishing));
		return bookParams;
	}
}
