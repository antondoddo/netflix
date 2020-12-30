package com.antondoddo.production.repository.exception;

public class CouldNotAddProductionException extends RuntimeException {
  public CouldNotAddProductionException(Throwable throwable) {
    super("Non ho potuto aggiungere la produzione", throwable);
  }
}
