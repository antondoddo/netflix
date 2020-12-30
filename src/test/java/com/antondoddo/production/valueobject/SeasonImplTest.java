package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.antondoddo.production.valueobject.exception.IllegalSeasonException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class SeasonImplTest {

  protected static Object[] shouldBeThrowIllegalSeasonExceptionData() {
    return new Object[]{
        new Object[]{
            0
        }, new Object[]{
        -7
    }, new Object[]{
        60
    }
    };
  }

  @Test
  @Parameters(method = "shouldBeThrowIllegalSeasonExceptionData")
  public void shouldBeThrowIllegalSeasonException(int value) {
    IllegalSeasonException expected = null;
    try {
      SeasonImpl season = new SeasonImpl(value);
    } catch (IllegalSeasonException ex) {
      expected = ex;
    }
    assertNotNull(expected);
    assertEquals("Numero della stagione non valido,"
        + " può essere compreso solo tra 1 e 50", expected.toString());
  }

  @Test
  public void shouldBeReturnValue() {
    SeasonImpl season = new SeasonImpl(1);
    assertEquals(1, season.getValue());
  }

  @Test
  public void shouldReturnToString() {
    SeasonImpl season = new SeasonImpl(1);
    assertEquals("Stagione " + 1, season.toString());
  }

  protected static Object[] shouldBeEqualsData() {
    SeasonImpl season = new SeasonImpl(2);
    return new Object[]{
        new Object[]{
            season,
            season
        },
        new Object[]{
            new SeasonImpl(1),
            new SeasonImpl(1),
        }
    };
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(SeasonImpl season1, SeasonImpl season2) {
    assertEquals(season1, season2);
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
        new SeasonImpl(2),
        new SeasonImpl(3),
    };
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(SeasonImpl season1, SeasonImpl season2) {
    assertNotEquals(season1, season2);
  }
}
