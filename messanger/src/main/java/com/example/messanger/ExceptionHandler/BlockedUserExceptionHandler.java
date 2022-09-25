package com.example.messanger.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.messanger.errors.ErrorResponse;
import com.example.messanger.exceptions.BlockedUserException;

@ControllerAdvice
public class BlockedUserExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleEcxeption(BlockedUserException exc ){
		
	ErrorResponse error = new ErrorResponse()  ;
		
		error.setStatus(HttpStatus.NOT_FOUND.value());

		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		

		
		return new ResponseEntity <> (error , HttpStatus.NOT_FOUND)  ;
		
	}

}