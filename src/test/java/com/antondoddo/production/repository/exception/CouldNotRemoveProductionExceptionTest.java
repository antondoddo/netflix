package com.antondoddo.production.repository.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CouldNotRemoveProductionExceptionTest {

  @Test
  public void shouldBeThrowNotRemoveProductionException() {
    CouldNotRemoveProductionException exception =
        new CouldNotRemoveProductionException(new Exception());
    assertEquals("Non ho potuto cancellare la produzione", exception.getMessage());
  }
}
