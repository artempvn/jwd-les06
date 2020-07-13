package by.artempvn.les06.controller.command;

import java.util.Map;
import by.artempvn.les06.controller.exception.ControllerException;

public interface Command {

	Map<String, Object> execute(Map<String, String> params)
			throws ControllerException;
}
