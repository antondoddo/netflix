package com.antondoddo.production.valueobject.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IllegalEpisodeExceptionTest {

  @Test
  public void shouldBeThrowIllegaleEpisodeException() {
    IllegalEpisodeException exception = new IllegalEpisodeException();
    assertEquals("Numero dell'episodio non valido, "
        + "può essere compreso solo tra 1 e 1000", exception.toString());
  }
}
