package br.com.serratec.monitoria.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final HttpStatus httpStatus;

	public CustomException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	public CustomException(String message) {
		this(message, HttpStatus.NOT_FOUND);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
