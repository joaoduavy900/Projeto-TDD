package com.ufcg.exceptions;

public class InvalidTicketIdException extends RuntimeException {
  public InvalidTicketIdException(String message) {
    super(message);
  }

  public InvalidTicketIdException(String message, Throwable cause) {
    super(message, cause);
  }
}
