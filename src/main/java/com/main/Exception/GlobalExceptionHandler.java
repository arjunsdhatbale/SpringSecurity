package com.main.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ObjectOptimisticLockingFailureException.class)
	public ResponseEntity<Map<String, Object>> handleOptimisticLockingFailure(
			ObjectOptimisticLockingFailureException e){
		
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("error", "Conflict");
		errorResponse.put("message", e.getMessage());
		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("status", 409);
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
}
