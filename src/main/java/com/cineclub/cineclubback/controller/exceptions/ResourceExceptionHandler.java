package com.cineclub.cineclubback.controller.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cineclub.cineclubback.services.exceptions.DataException;
import com.cineclub.cineclubback.services.exceptions.ResourceNotFoundException;



@ControllerAdvice
public class ResourceExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPaht(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	
	
	@ExceptionHandler(DataException.class)
	public ResponseEntity<StandardError> dataBase(ResourceNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPaht(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	@ExceptionHandler(CrudExeption.class)
	public ResponseEntity<StandardError> crudExeption(CrudExeption e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setError("Something is wrong with the controller XD");
		err.setMessage(e.getMessage());
		err.setPaht(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}

