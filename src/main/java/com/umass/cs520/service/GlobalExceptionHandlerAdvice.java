package com.umass.cs520.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(1)
public class GlobalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

  Logger logger = Logger.getLogger(GlobalExceptionHandlerAdvice.class.getName());

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
    logger.log(Level.SEVERE, "Exception: " + ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
  }

  private String getRequestUri(WebRequest request) {
    return ((ServletWebRequest) request).getRequest().getRequestURI();
  }
}
