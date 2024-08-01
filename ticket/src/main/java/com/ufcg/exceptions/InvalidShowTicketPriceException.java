package com.ufcg.exceptions;

public class InvalidShowTicketPriceException extends RuntimeException {
  public InvalidShowTicketPriceException(String message) {
    super(message);
  }

  public InvalidShowTicketPriceException(String message, Throwable cause) {
    super(message, cause);
  }
}
