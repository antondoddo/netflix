package com.antondoddo.production.valueobject.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class IllegalYearOfPublicationExceptionTest {

  @Test
  public void shouldBeThrowIllegalYearOfPublicationException() {
    IllegalYearOfPublicationException exception = new IllegalYearOfPublicationException();
    assertEquals("La data non è valida", exception.toString());
  }
}
