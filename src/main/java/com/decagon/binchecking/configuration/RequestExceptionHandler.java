package com.decagon.binchecking.configuration;

import com.decagon.binchecking.apiresponse.ApiResponse;
import com.decagon.binchecking.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * RequestExceptionHandler
 */
@ControllerAdvice
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException() {
		ApiResponse<?> ar = new ApiResponse<>();
		ar.setSuccess(false);
		return new ResponseEntity<>(ar, HttpStatus.NOT_FOUND);
	}

}