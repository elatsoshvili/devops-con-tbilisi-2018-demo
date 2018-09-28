package ge.devopsgeorgia.demo.controller;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import ge.devopsgeorgia.demo.exception.InvalidInputException;

public abstract class BaseController {

	protected void generateErrorMessage(Errors errors) {
		if (errors.hasErrors()) {
			String body = "";
			List<ObjectError> errorList = errors.getAllErrors();
			for (ObjectError error : errorList) {
				body += error.getDefaultMessage();
				body += "; ";
			}
			
			throw new InvalidInputException(body);
	    }
	}
	
}