package com.example.hexagonal.infrastructure.web.error;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.hexagonal.domain.error.DomainEntityNotFoundException;

public class GlobalErrorController extends ResponseEntityExceptionHandler {

    ProblemDetail handleEntityNotFoundException(DomainEntityNotFoundException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not Found");
        problemDetail.setType(URI.create("https://example.com/errors/not-found"));
        return problemDetail;
    }

}
