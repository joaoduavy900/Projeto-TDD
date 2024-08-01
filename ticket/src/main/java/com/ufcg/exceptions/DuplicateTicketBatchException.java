package com.ufcg.exceptions;

public class DuplicateTicketBatchException extends RuntimeException {
  public DuplicateTicketBatchException(String message) {
    super(message);
  }

  public DuplicateTicketBatchException(String message, Throwable cause) {
    super(message, cause);
  }
}
