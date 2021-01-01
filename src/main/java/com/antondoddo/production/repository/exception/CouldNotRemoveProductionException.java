package com.antondoddo.production.repository.exception;

public class CouldNotRemoveProductionException extends RuntimeException {
  public CouldNotRemoveProductionException(Throwable throwable) {
    super("Non ho potuto cancellare la produzione", throwable);
  }

  public CouldNotRemoveProductionException() {
    super("Non ho potuto cancellare la produzione");
  }
}
