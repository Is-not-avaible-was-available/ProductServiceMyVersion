package dev.rajat.ProductServiceMyVersion.Exceptions;

import dev.rajat.ProductServiceMyVersion.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND
                , notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }
}
