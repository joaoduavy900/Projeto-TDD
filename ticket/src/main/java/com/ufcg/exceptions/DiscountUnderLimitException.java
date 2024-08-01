package com.ufcg.exceptions;

public class DiscountUnderLimitException extends RuntimeException {
  public DiscountUnderLimitException(String message) {
    super(message);
  }

  public DiscountUnderLimitException(String message, Throwable cause) {
    super(message, cause);
  }
}
