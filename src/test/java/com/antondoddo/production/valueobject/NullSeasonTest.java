package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public final class NullSeasonTest {

  @Test
  public void shouldReturnValue() {
    NullSeason nullSeason = new NullSeason();
    assertEquals(Integer.valueOf(0), nullSeason.getValue());
  }

  @Test
  public void shouldReturnNullSeasonString() {
    NullSeason nullSeason = new NullSeason();
    assertEquals("No season", nullSeason.toString());
  }

  protected static Object[] shouldBeEqualsData() {
    return new Object[]{
      new Object[]{
        new NullSeason(),
        new NullSeason()
      }
    };
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(NullSeason season1, NullSeason season2) {
    assertEquals(season1, season2);
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
      new Object[]{
        new NullSeason(),
        "No season"
      },
      new Object[]{
        new NullSeason(),
        0
      }
    };
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(NullSeason season1, Object obj) {
    assertNotEquals(season1, obj);
  }
}
