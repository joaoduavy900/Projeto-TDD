package com.ufcg.exceptions;

public class NoAvailableTicketException extends RuntimeException {
  public NoAvailableTicketException(String message) {
    super(message);
  }

  public NoAvailableTicketException(String message, Throwable cause) {
    super(message, cause);
  }
}
