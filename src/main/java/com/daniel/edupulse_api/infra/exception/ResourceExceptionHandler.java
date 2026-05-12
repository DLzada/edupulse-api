package com.daniel.edupulse_api.infra.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> duplicateKey(DataIntegrityViolationException e, HttpServletRequest request){
        String msg = "Erro de integridade: Este código INEP já está cadastrado ou há dados faltando.";
        StandardError err = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.CONFLICT.value(),
                "Data Integrity Violation",
                msg,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> resourceNotFound(NoSuchElementException e, HttpServletRequest request){
        StandardError err = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Rsource Not Found",
                "O recurso solicitado não foi encontrado no sistema.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
