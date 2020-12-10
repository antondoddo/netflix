package com.antondoddo.production.valueobject.exception;

public final class IllegalDurationException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalDurationException() {
    super("Il film è troppo corto o troppo lungo");
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
