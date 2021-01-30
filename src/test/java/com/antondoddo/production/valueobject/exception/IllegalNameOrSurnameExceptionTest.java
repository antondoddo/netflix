package com.antondoddo.production.valueobject.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class IllegalNameOrSurnameExceptionTest {

  @Test
  public void shouldBeThrowIllegalNameOrSurnameException() {
    IllegalNameOrSurnameException exception = new IllegalNameOrSurnameException("Actor");
    assertEquals("Actor" + ": Il nome o il cognome non sono validi", exception.toString());
  }
}
