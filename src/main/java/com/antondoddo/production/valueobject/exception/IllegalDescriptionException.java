package com.antondoddo.production.valueobject.exception;

import com.antondoddo.production.valueobject.Description;

public final class IllegalDescriptionException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalDescriptionException() {
    super(": la descrizione deve contenere più di "
            + Description.min + " caratteri ma meno di "
            + Description.max);
  }

  @Override
  public String toString() {
    return getMessage();
  }
}


