package com.antondoddo.production.repository.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CouldNotAddProductionExceptionTest {

  @Test
  public void shouldBeThrowNotAddProductionException() {
    CouldNotAddProductionException exception = new CouldNotAddProductionException(new Exception());
    assertEquals("Non ho potuto aggiungere la produzione", exception.getMessage());
  }
}
