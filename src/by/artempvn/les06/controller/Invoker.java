package by.artempvn.les06.controller;

import java.util.HashMap;
import java.util.Map;
import by.artempvn.les06.controller.command.Command;
import by.artempvn.les06.controller.exception.ControllerException;
import by.artempvn.les06.validator.InputDataValidator;

public class Invoker {
	private Command command;

	public Invoker(Command command) {
		this.command = command;
	}

	public Map<String, Object> processRequest(Map<String, String> params)
			throws ControllerException {
		InputDataValidator dataValidator = new InputDataValidator();
		Map<String, Object> output = new HashMap<>();
		if (params != null && dataValidator.isNotNullMapValue(params)) {
			output = command.execute(params);
		} else {
			throw new ControllerException("null data value");
		}
		return output;
	}
}
