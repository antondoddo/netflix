package com.antondoddo.production.repository.exception;

public final class CouldNotFindProductionException extends RuntimeException {
  private static final String message = "Non ho potuto trovare nulla";

  public CouldNotFindProductionException(Throwable throwable) {
    super(message, throwable);
  }

  public CouldNotFindProductionException() {
    super(message);
  }
}
