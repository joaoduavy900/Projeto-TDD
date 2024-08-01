package com.ufcg.exceptions;

public class InvalidTicketBatchDiscountException extends RuntimeException {
  public InvalidTicketBatchDiscountException(String message) {
    super(message);
  }

  public InvalidTicketBatchDiscountException(String message, Throwable cause) {
    super(message, cause);
  }
}
