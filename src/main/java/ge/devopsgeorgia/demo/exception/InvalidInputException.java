package ge.devopsgeorgia.demo.exception;

import org.springframework.http.HttpStatus;


public class InvalidInputException extends BaseRestException {

	private static final long serialVersionUID = 1L;

	public InvalidInputException(String message) {
    	super(message, HttpStatus.BAD_REQUEST);
    }
}