package com.antondoddo.production.valueobject.exception;

import com.antondoddo.production.valueobject.Description;

public class IllegalDescriptionException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;


  public IllegalDescriptionException() {
    super("Descrizione non valida");
  }

  @Override
  public String toString() {
    return ": la descrizione deve contenere più di "
            + Description.min + " caratteri ma meno di "
            + Description.max;
  }
}
