package com.project.ecommerce.exception;

public class InvalidPhoneNumberException extends RuntimeException {
  public InvalidPhoneNumberException(String message) {
    super(message);
  }
}
