package com.antondoddo.production.valueobject.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IllegalTitleExceptionTest {

  @Test
  public void shouldBeThrowIllegalTitleException() {
    IllegalTitleException exception = new IllegalTitleException();
    assertEquals("Il titolo inserito non è valido", exception.toString());
  }
}
