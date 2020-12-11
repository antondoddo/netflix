package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import com.antondoddo.production.valueobject.exception.IllegalSeasonException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RealSeasonTest {

  protected static Object[] shouldBeThrownIllegalSeasonExceptionData() {
    return new Object[]{
        "0",
        "-1",
    };
  }

  @Test
  @Parameters(method = "shouldBeThrownIllegalSeasonExceptionData")
  public void shouldBeThrownIllegalSeasonException(Integer value) {
    IllegalSeasonException exception = null;
    try {
      new RealSeason(value);
    } catch (IllegalSeasonException e) {
      exception = e;
    }
    assertNotNull(exception);
    assertEquals("La stagione non è valida", exception.toString());
  }

  protected static Object[] shouldReturnSameParameterPassedViaTheConstructorData() {
    return new Object[]{
        "10",
    };
  }

  @Test
  @Parameters(method = "shouldReturnSameParameterPassedViaTheConstructorData")
  public void shouldReturnSameParameterPassedViaTheConstructor(Integer value) {
    RealSeason season = new RealSeason(value);
    assertSame(value, season.getValue());
  }

  protected static Object[] shouldBeEqualsData() {
    RealSeason season = new RealSeason(10);
    return new Object[]{
        new Object[]{new RealSeason(10), new RealSeason(10)},
        new Object[]{season, season},
    };
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(RealSeason season1, RealSeason season2) {
    assertEquals(season1, season2);
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
        new Object[]{new RealSeason(10), new RealSeason(11)},
    };
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(RealSeason season1, RealSeason season2) {
    assertNotEquals(season1, season2);
  }
}
