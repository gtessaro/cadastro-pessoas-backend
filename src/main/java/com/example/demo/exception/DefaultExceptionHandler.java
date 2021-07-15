package com.example.demo.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Para formatar a mensagem de erro disparada através dos Beans Validations...
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, 
		HttpHeaders headers, 
		HttpStatus status, 
		WebRequest request
	) {

		List<String> errors = new ArrayList<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String errorMessage = error.getDefaultMessage();
	        errors.add(errorMessage);
	    });

	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(
		UsuarioCadastradoException.class
	)
	public final ResponseEntity<Object> handleUsuarioCadastradoExceptionException(
			UsuarioCadastradoException  ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) throws Exception {
		List<String> errors = new ArrayList<>();
		
		status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = ex.getMessage();
		
		System.out.println(message);
		errors.add(message);
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		
	}
	
}
