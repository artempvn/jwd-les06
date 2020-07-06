package by.artempvn.les06.parser;

import by.artempvn.les06.exception.CustomException;

public class DataParser {
//TODO remove later maybe
	public String[] findFields(String input, String delimiter)
			throws CustomException {
		if (input == null || input.isEmpty()) {
			throw new CustomException("Null or empty input data");
		}
		String[] fields = input.split(delimiter);
		return fields;
	}

}
