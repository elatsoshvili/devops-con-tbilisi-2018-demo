package ge.devopsgeorgia.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String body = String.format("{\"message\":\"%s\"}", ex.getMessage());
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		if (ex instanceof BaseRestException) {
			status = ((BaseRestException)ex).getStatusCode();
		}
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	}
}