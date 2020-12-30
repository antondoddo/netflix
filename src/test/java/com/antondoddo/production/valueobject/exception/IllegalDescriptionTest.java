package com.antondoddo.production.valueobject.exception;

import static org.junit.Assert.assertEquals;

import com.antondoddo.production.valueobject.Description;
import org.junit.Test;

public class IllegalDescriptionTest {

  @Test
  public void shouldBeReturnIllegalDescriptionException() {
    IllegalDescriptionException exception = new IllegalDescriptionException();
    assertEquals(": la descrizione deve contenere più di "
        + Description.min + " caratteri ma meno di "
        + Description.max, exception.toString());
  }
}
