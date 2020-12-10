package com.antondoddo.production.valueobject.exception;

public final class IllegalNameOrSurnameException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;
  private final String role;

  public IllegalNameOrSurnameException(String role) {
    super(role + ": Il nome o il cognome non sono validi");
    this.role = role;
  }

  @Override
  public String toString() {
    return getMessage();
  }

}
