package com.antondoddo.production.valueobject.exception;

public class IllegalSeasonException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalSeasonException() {
    super("Numero della stagione non valido,"
        + " può essere compreso solo tra 1 e 50");
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
