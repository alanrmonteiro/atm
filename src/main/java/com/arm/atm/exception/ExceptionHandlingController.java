package com.arm.atm.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(AtmException.class)
	public ResponseEntity<String> conflict(HttpServletRequest req, Exception ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
	}
}
