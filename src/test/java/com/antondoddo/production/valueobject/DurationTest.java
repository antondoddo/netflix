package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.antondoddo.production.valueobject.exception.IllegalDurationException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DurationTest {

  protected static Object[] shouldBeThrowIllegalDurationExceptionData() {
    return new Object[]{
        new Object[]{
            java.time.Duration.ofSeconds(86401),
        },
        new Object[]{
            java.time.Duration.ofSeconds(0),
        }
    };
  }

  protected static Object[] shouldBeEqualsData() {
    java.time.Duration duration = java.time.Duration.ofSeconds(5600);
    return new Object[]{
        new Object[]{
            java.time.Duration.ofSeconds(5600),
            java.time.Duration.ofSeconds(5600)
        },
        new Object[]{
            duration,
            duration
        }
    };
  }

  protected static Object[] shouldBeNotEqualsData() {
    java.time.Duration duration = java.time.Duration.ofSeconds(5600);
    return new Object[]{
        new Object[]{
            java.time.Duration.ofSeconds(5600),
            java.time.Duration.ofSeconds(9300)
        }
    };
  }

  @Test
  @Parameters(method = "shouldBeThrowIllegalDurationExceptionData")
  public void shouldBeThrowIllegalDurationException(java.time.Duration filmDuration) {
    IllegalDurationException expected = null;
    try {
      Duration duration = new Duration(filmDuration);
    } catch (IllegalDurationException exception) {
      expected = exception;
    }
    assertNotNull(expected);
    assertEquals(expected.toString(), "Il film è troppo corto o troppo lungo");
  }

  @Test
  public void shouldBeReturnFilmDuration() {
    Duration filmDuration = new Duration(java.time.Duration.ofSeconds(4400));
    assertEquals(java.time.Duration.ofSeconds(4400), filmDuration.getTimeDuration());
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(java.time.Duration duration1, java.time.Duration duration2) {
    assertEquals(duration1, duration2);
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(java.time.Duration duration1, java.time.Duration duration2) {
    assertNotEquals(duration1, duration2);
  }
}
