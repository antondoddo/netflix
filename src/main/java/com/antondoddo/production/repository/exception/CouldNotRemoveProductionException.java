package com.antondoddo.production.repository.exception;

public final class CouldNotRemoveProductionException extends RuntimeException {

  private static final String message = "Non ho potuto cancellare la produzione";

  public CouldNotRemoveProductionException(Throwable throwable) {
    super(message, throwable);
  }

  public CouldNotRemoveProductionException() {
    super(message);
  }
}
