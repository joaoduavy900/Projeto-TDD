package com.ufcg.exceptions;

public class DiscountOverLimitException extends RuntimeException {
  public DiscountOverLimitException(String message) {
    super(message);
  }

  public DiscountOverLimitException(String message, Throwable cause) {
    super(message, cause);
  }
}
