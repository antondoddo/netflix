package com.antondoddo.production.repository.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public final class CouldNotAddProductionExceptionTest {

  @Test
  public void shouldBeThrowNotAddProductionExceptionWithThrowable() {
    Exception throwable = new Exception();
    CouldNotAddProductionException exception = new CouldNotAddProductionException(throwable);
    assertEquals("Non ho potuto aggiungere la produzione", exception.getMessage());
    assertSame(throwable, exception.getCause());
  }

  @Test
  public void shouldBeThrowNotAddProductionException() {
    CouldNotAddProductionException exception = new CouldNotAddProductionException(new Exception());
    assertEquals("Non ho potuto aggiungere la produzione", exception.getMessage());
  }
}
