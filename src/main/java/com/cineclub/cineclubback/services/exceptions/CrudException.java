package com.cineclub.cineclubback.services.exceptions;

public class CrudException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CrudException(String msg) {
		super(msg);
	}
}