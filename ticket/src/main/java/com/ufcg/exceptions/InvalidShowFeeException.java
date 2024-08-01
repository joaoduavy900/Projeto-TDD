package com.ufcg.exceptions;

public class InvalidShowFeeException extends RuntimeException {
  public InvalidShowFeeException(String message) {
    super(message);
  }

  public InvalidShowFeeException(String message, Throwable cause) {
    super(message, cause);
  }
}
