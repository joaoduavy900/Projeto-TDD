package com.ufcg.exceptions;

public class HalfPriceTicketsOverLimitException extends RuntimeException {
  public HalfPriceTicketsOverLimitException(String message) {
    super(message);
  }

  public HalfPriceTicketsOverLimitException(String message, Throwable cause) {
    super(message, cause);
  }
}
