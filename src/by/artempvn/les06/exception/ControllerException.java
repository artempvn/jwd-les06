package by.artempvn.les06.exception;

public class ControllerException extends Exception {
	public ControllerException() {
	}

	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}
}
