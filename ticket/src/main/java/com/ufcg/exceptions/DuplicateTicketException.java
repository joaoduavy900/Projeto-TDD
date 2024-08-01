package com.ufcg.exceptions;

public class DuplicateTicketException extends RuntimeException {
  public DuplicateTicketException(String message) {
    super(message);
  }

  public DuplicateTicketException(String message, Throwable cause) {
    super(message, cause);
  }
}
