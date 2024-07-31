package com.ufcg.exceptions;

public class InvalidShowCostException extends RuntimeException {
  public InvalidShowCostException(String message) {
    super(message);
  }

  public InvalidShowCostException(String message, Throwable cause) {
    super(message, cause);
  }
}
