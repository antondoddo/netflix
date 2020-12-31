package com.antondoddo.production.repository.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CouldNotFindProductionExceptionTest {

  @Test
  public void shouldBeThrowNotFindProductionException() {
    CouldNotFindProductionException exception =
        new CouldNotFindProductionException(new Exception());
    assertEquals("Non ho potuto trovare nulla", exception.getMessage());
  }
}
