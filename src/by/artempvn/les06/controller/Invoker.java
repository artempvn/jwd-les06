package by.artempvn.les06.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import by.artempvn.les06.controller.command.Command;
import by.artempvn.les06.exception.ControllerException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.validator.InputDataValidator;

public class Invoker {

	private static Invoker invoker;

	private Invoker() {
	}

	public static Invoker getInstance() {
		if (invoker == null) {
			invoker = new Invoker();
		}
		return invoker;
	}

	public Optional<Map<String, List<Book>>> processRequest(String command,
			Map<String, String> params) throws ControllerException {
		InputDataValidator dataValidator = new InputDataValidator();
		Optional<Map<String, List<Book>>> output = Optional.empty();
		Command userCommand = InvokerProvider.defineCommand(command);
		if (params != null && dataValidator.isNotNullMapValue(params)) {
			output = Optional.of(userCommand.execute(params));
		}
		return output;
	}
}
