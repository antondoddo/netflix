package com.antondoddo.production.valueobject.exception;

public class IllegalYearOfPublicationException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalYearOfPublicationException() {

    super("DataException:");
  }

  @Override
  public String toString() {

    return getMessage() + ": La data non è valida";
  }
}
