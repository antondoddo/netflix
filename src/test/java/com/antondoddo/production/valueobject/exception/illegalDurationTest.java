package com.antondoddo.production.valueobject.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class illegalDurationTest {

  @Test
  public void shouldBeThrowIllegalDurationTest() {
    IllegalDurationException exception = new IllegalDurationException();
    assertEquals("Il film è troppo corto o troppo lungo", exception.toString());
  }
}
