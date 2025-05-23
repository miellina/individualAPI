package org.serratec.backend.trabalhoIndividual.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FuncionarioException.class)
	protected ResponseEntity<Object> handleEmailException(FuncionarioException ex) {
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
	}
}