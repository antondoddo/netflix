package com.antondoddo.production.valueobject.exception;

public final class IllegalYearOfPublicationException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalYearOfPublicationException() {
    super("La data non è valida");
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
