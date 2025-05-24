package com.project.ecommerce.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidPasswordException(InvalidPasswordException e, HttpServletRequest request) {
         ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidPhoneNumberException(InvalidPhoneNumberException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidEmailException(InvalidEmailException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidCpfException(InvalidCpfException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBirthDateException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidBirthDateException(InvalidBirthDateException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCpfAlreadyExists(CpfAlreadyExistsException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        String message = e.getMessage();
        if (message != null && message.contains("customers_cpf_key")) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), message, request.getRequestURI(), LocalDateTime.now());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerNotFoundException(CustomerNotFoundException e, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
