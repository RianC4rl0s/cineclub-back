package com.cineclub.cineclubback.controller.exceptions;

public class CrudExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CrudExeption(String msg) {
		super(msg);
	}
}