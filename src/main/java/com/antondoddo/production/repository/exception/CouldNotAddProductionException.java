package com.antondoddo.production.repository.exception;

public final class CouldNotAddProductionException extends RuntimeException {

  private static final String message = "Non ho potuto aggiungere la produzione";

  public CouldNotAddProductionException(Throwable throwable) {
    super(message, throwable);
  }

  public CouldNotAddProductionException() {
    super(message);
  }
}
