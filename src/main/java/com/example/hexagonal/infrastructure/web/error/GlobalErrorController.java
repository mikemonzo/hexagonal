package com.example.hexagonal.infrastructure.web.error;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.hexagonal.domain.error.DomainEntityNotFoundException;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainEntityNotFoundException.class)
    ProblemDetail handleEntityNotFoundException(DomainEntityNotFoundException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not Found");
        problemDetail.setType(URI.create("https://example.com/errors/not-found"));
        return problemDetail;
    }

}
