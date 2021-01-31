package com.antondoddo.production.valueobject.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class IllegalReleaseDateExceptionTest {

  @Test
  public void shouldBeThrowIllegalReleaseDateException() {
    IllegalReleaseDateException exception = new IllegalReleaseDateException();
    assertEquals("La data non è valida", exception.toString());
  }
}
