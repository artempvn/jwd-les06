package by.artempvn.les06.controller.command;

import java.util.List;
import java.util.Map;

import by.artempvn.les06.exception.ControllerException;
import by.artempvn.les06.model.entity.Book;

public interface Command {

	Map<String, List<Book>> execute(Map<String, String> params)
			throws ControllerException;
}
