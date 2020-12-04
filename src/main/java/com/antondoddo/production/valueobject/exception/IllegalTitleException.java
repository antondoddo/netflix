package com.antondoddo.production.valueobject.exception;

public class IllegalTitleException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalTitleException() {
    super("Invalid Title");
  }

  @Override
  public String toString() {
    return getMessage() + " :Il titolo inserito non è valido";
  }
}
