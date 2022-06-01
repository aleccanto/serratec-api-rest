package br.com.serratec.monitoria.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<String> handleCustomException(CustomException ex, WebRequest wb) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("", "");
//		return new ResponseEntity<>(ex.getMessage(),headers, HttpStatus.NOT_FOUND);

//		return ResponseEntity.notFound()
//							 .header("", "")
//							 .build();

		return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
	}
}