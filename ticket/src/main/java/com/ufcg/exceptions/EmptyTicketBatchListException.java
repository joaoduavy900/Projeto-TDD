package com.ufcg.exceptions;

public class EmptyTicketBatchListException extends RuntimeException {
  public EmptyTicketBatchListException(String message) {
    super(message);
  }

  public EmptyTicketBatchListException(String message, Throwable cause) {
    super(message, cause);
  }
}
