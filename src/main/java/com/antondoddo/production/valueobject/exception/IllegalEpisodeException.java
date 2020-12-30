package com.antondoddo.production.valueobject.exception;

public class IllegalEpisodeException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalEpisodeException() {
    super(
        "Numero dell'episodio non valido, "
            + "può essere compreso solo tra 1 e 1000");
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
