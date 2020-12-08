package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class NullSeasonTest {

  @Test
  public void shouldReturnValue() {
    NullSeason nullSeason = new NullSeason();
    assertEquals(0, nullSeason.getValue());
  }

  @Test
  public void shouldReturnANullSeasonString() {
    NullSeason nullSeason = new NullSeason();
    assertEquals("No season", nullSeason.toString());
  }
}
