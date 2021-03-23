package br.com.cbgomes.acme.client.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cbgomes.acme.client.service.exception.NotFoundException;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> NotFound(NotFoundException error, HttpServletRequest request){
		StandardError error_view = new StandardError(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(),error.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error_view);
	}
}