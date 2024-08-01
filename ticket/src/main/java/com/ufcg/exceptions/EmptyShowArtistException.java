package com.ufcg.exceptions;

public class EmptyShowArtistException extends RuntimeException {
  public EmptyShowArtistException(String message) {
    super(message);
  }

  public EmptyShowArtistException(String message, Throwable cause) {
    super(message, cause);
  }
}
