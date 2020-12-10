package com.antondoddo.production.valueobject.exception;

public final class IllegalTitleException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalTitleException() {
    super("Il titolo inserito non è valido");
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
