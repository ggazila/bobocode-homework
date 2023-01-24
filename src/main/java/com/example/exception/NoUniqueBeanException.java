package com.example.exception;

public class NoUniqueBeanException extends RuntimeException {
	public NoUniqueBeanException(String message) {
		super(message);
	}
}
