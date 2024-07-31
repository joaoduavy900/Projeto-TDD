package com.ufcg.exceptions;

public class VipTicketsOverLimitException extends RuntimeException {
  public VipTicketsOverLimitException(String message) {
    super(message);
  }

  public VipTicketsOverLimitException(String message, Throwable cause) {
    super(message, cause);
  }
}
