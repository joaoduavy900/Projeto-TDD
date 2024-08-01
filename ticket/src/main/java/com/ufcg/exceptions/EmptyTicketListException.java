package com.ufcg.exceptions;

public class EmptyTicketListException extends RuntimeException {
  public EmptyTicketListException(String message) {
    super(message);
  }

  public EmptyTicketListException(String message, Throwable cause) {
    super(message, cause);
  }
}
