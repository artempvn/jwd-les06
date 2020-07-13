package by.artempvn.les06.model.exception;

public class ModelException extends Exception {

	public ModelException() {
	}

	public ModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModelException(String message) {
		super(message);
	}

	public ModelException(Throwable cause) {
		super(cause);
	}
}