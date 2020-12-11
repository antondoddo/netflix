package com.antondoddo.production.valueobject.exception;

public final class IllegalSeasonException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalSeasonException() {
    super("La stagione non è valida");
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
