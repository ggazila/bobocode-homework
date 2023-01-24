package com.example.exception;

public class NoSuchBeanException extends RuntimeException {
	public NoSuchBeanException(String message) {
		super(message);
	}
}
