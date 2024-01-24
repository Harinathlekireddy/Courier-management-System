package Exceptions;

public class invalidEmployeeIdException extends RuntimeException {
	public invalidEmployeeIdException(String message) {
		super(message);
	}
}