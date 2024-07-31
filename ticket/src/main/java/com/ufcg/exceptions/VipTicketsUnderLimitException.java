package com.ufcg.exceptions;

public class VipTicketsUnderLimitException extends RuntimeException {
  public VipTicketsUnderLimitException(String message) {
    super(message);
  }

  public VipTicketsUnderLimitException(String message, Throwable cause) {
    super(message, cause);
  }
}
