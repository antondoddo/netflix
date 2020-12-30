package com.antondoddo.production.repository.exception;

public class CouldNotFindProductionException extends RuntimeException {
  public CouldNotFindProductionException(Throwable throwable) {
    super("Non ho potuto trovare nulla", throwable);
  }
}
