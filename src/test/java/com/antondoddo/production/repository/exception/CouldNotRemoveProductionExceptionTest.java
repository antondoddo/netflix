package com.antondoddo.production.repository.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public final class CouldNotRemoveProductionExceptionTest {

  @Test
  public void shouldBeThrowNotRemoveProductionExceptionWithThrowable() {
    Exception throwable = new Exception();
    CouldNotRemoveProductionException exception =
        new CouldNotRemoveProductionException(throwable);
    assertEquals("Non ho potuto cancellare la produzione", exception.getMessage());
    assertSame(throwable, exception.getCause());
  }

  @Test
  public void shouldBeThrowNotRemoveProductionException() {
    CouldNotRemoveProductionException exception =
        new CouldNotRemoveProductionException(new Exception());
    assertEquals("Non ho potuto cancellare la produzione", exception.getMessage());
  }
}
