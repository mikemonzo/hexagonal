package com.example.hexagonal.infrastructure.web.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.hexagonal.domain.error.DomainEntityNotFoundException;
import com.example.hexagonal.domain.error.DomainValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainEntityNotFoundException.class)
    ProblemDetail handleEntityNotFoundException(DomainEntityNotFoundException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not Found");
        problemDetail.setType(URI.create("http://www.openwebinars.net/error/not-found"));
        return problemDetail;
    }

    @ExceptionHandler(DomainValidationException.class)
    ProblemDetail handleEntityNotFoundException(DomainValidationException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        problemDetail.setTitle("Domain validation error");
        problemDetail.setType(URI.create("http://www.openwebinars.net/error/domain-error"));
        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(
            ConstraintViolationException exception) {
        return createProblemDetailWithInvalidParams(exception);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException exception, @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        ProblemDetail body = createProblemDetailWithInvalidParams(exception);
        return this.handleExceptionInternal(exception, body, headers, HttpStatusCode.valueOf(400),
                request);
    }

    private ProblemDetail createProblemDetailWithInvalidParams(Exception e) {

        ProblemDetail result = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400),
                "Error de validación");
        result.setTitle("Error de validación");
        result.setType(URI.create("http://www.openwebinars.net/error/validation-error"));
        result.setProperty("datetime", DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")
                .withZone(ZoneId.systemDefault()).format(Instant.now()));

        if (e instanceof MethodArgumentNotValidException ex1) {
            List<ApiValidationSubError> invalidParams = ex1.getAllErrors().stream()
                    .map(ApiValidationSubError::fromObjectError).toList();
            result.setProperty("invalid-params", invalidParams);

        } else if (e instanceof ConstraintViolationException ex2) {
            List<ApiValidationSubError> invalidParams = ex2.getConstraintViolations().stream()
                    .map(ApiValidationSubError::fromConstraintViolation).toList();
            result.setProperty("invalid-params", invalidParams);

        } else {
            result = null;
        }

        return result;

    }


    @Builder
    public record ApiValidationSubError(String object, String message,
            @JsonInclude(JsonInclude.Include.NON_NULL) String field,
            @JsonInclude(JsonInclude.Include.NON_NULL) Object rejectedValue) {
        public static ApiValidationSubError fromObjectError(ObjectError objectError) {
            if (objectError instanceof FieldError fieldError) {

                return ApiValidationSubError.builder().object(fieldError.getObjectName())
                        .field(fieldError.getField()).rejectedValue(fieldError.getRejectedValue())
                        .message(fieldError.getDefaultMessage()).build();

            } else {
                return ApiValidationSubError.builder().object(objectError.getObjectName())
                        .message(objectError.getDefaultMessage()).build();

            }
        }

        public static ApiValidationSubError fromConstraintViolation(ConstraintViolation<?> v) {
            return ApiValidationSubError.builder().message(v.getMessage())
                    .rejectedValue(v.getInvalidValue())
                    .object(v.getRootBean().getClass().getSimpleName())
                    .field(((PathImpl) v.getPropertyPath()).getLeafNode().asString()).build();
        }

    }
}
