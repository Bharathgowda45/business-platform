package com.business.platform.common;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
 ResponseEntity<?> validation(Exception e){return ResponseEntity.badRequest().body(Map.of("timestamp",Instant.now(),"message","Validation failed"));}
 @ExceptionHandler(java.util.NoSuchElementException.class)
 ResponseEntity<?> notFound(Exception e){return ResponseEntity.status(404).body(Map.of("timestamp",Instant.now(),"message","Resource not found"));}
}
