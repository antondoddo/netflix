package com.antondoddo.production.valueobject.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IllegalSeasonExceptionTest {

  @Test
  public void shouldBeThrowIllegalSeasonException() {
    IllegalSeasonException exception = new IllegalSeasonException();
    assertEquals("Numero della stagione non valido,"
        + " può essere compreso solo tra 1 e 50", exception.toString());
  }
}
