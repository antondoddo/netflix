package com.antondoddo.production.valueobject.exception;

public final class IllegalReleaseDateException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalReleaseDateException() {
    super("La data non è valida");
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
