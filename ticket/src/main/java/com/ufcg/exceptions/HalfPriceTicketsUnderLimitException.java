package com.ufcg.exceptions;

public class HalfPriceTicketsUnderLimitException extends RuntimeException {
  public HalfPriceTicketsUnderLimitException(String message) {
    super(message);
  }

  public HalfPriceTicketsUnderLimitException(String message, Throwable cause) {
    super(message, cause);
  }
}
