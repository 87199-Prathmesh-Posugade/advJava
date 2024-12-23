package com.sunbeam.exceptionC;

public class ApiException extends RuntimeException {
	public ApiException(String message) {
		super(message);
	}
}
