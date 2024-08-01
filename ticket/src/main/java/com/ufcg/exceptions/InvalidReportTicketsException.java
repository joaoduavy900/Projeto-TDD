package com.ufcg.exceptions;

public class InvalidReportTicketsException extends RuntimeException {
  public InvalidReportTicketsException(String message) {
    super(message);
  }

  public InvalidReportTicketsException(String message, Throwable cause) {
    super(message, cause);
  }
}
