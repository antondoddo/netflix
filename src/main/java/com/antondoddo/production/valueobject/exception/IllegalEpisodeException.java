package com.antondoddo.production.valueobject.exception;

public final class IllegalEpisodeException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalEpisodeException() {
    super("L'episodio non è valido");
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
