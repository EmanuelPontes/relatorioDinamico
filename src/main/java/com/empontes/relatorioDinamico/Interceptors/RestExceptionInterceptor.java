package com.empontes.relatoriodinamico.interceptors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionInterceptor extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Objeto não encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ 
        ConstraintViolationException.class, 
        DataIntegrityViolationException.class })
      public ResponseEntity<Object> handleBadRequest(
        Exception ex, WebRequest request) {
          return handleExceptionInternal(ex, ex.getLocalizedMessage(), 
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
      }
}
