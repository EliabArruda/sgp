package com.eliab.sistemas.sgp.handle;

import com.eliab.sistemas.sgp.exception.ProtocoloNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class ProtocoloControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ProtocoloNotFoundException.class)
    public final ResponseEntity<Object> handleProtocoloNotFoundException(ProtocoloNotFoundException exception,
                                                                         WebRequest request) {


        ErrorDetails exceptionResponse = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorDetails exceptionResponse = new ErrorDetails(LocalDateTime.now(),
                "Validation Failed",
                exception.getBindingResult().toString());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}