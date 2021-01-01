package com.antondoddo.production.repository.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class CouldNotFindProductionExceptionTest {

  @Test
  public void shouldBeThrowNotFindProductionExceptionWithThrowable() {
    Exception throwable = new Exception();
    CouldNotFindProductionException exception =
        new CouldNotFindProductionException(throwable);
    assertEquals("Non ho potuto trovare nulla", exception.getMessage());
    assertSame(throwable, exception.getCause());
  }

  @Test
  public void shouldBeThrowNotAddProductionException() {
    CouldNotFindProductionException exception =
        new CouldNotFindProductionException(new Exception());
    assertEquals("Non ho potuto trovare nulla", exception.getMessage());
  }
}
