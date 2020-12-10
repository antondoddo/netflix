package com.antondoddo.production.valueobject.exception;

public final class IllegalNameOrSurnameException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;


  public IllegalNameOrSurnameException(String role) {
    super(role + ": Il nome o il cognome non sono validi");
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
