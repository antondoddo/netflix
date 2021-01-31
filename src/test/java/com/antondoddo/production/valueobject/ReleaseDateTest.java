package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.antondoddo.production.valueobject.exception.IllegalReleaseDateException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ReleaseDateTest {

  protected static Object[] shouldBeThrowIllegalReleaseDateException() {
    return new Object[]{
        new Object[]{
            "20201-12-13"
        },
        new Object[]{
            "2020-14-13"
        },
        new Object[]{
            "2021-11-47"
        },
        new Object[]{
            "20201-16-33"
        }
    };
  }

  protected static Object[] shouldBeEqualsData() {
    ReleaseDate releaseDate = new ReleaseDate("1998-03-27");
    return new Object[]{
        new Object[]{
            new ReleaseDate("1993-01-19"),
            new ReleaseDate("1993-01-19"),
        },
        new Object[]{
            releaseDate,
            releaseDate
        }
    };
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
        new Object[]{
            new ReleaseDate("1993-01-19"),
            new ReleaseDate("1994-02-21"),
        },
    };
  }

  @Test
  @Parameters(method = "shouldBeThrowIllegalReleaseDateException")
  public void shouldBeThrowIllegalReleaseDate(String dateString) {
    IllegalReleaseDateException expected = null;
    try {
      ReleaseDate releaseDate = new ReleaseDate(dateString);
    } catch (IllegalReleaseDateException ex) {
      expected = ex;
    }
    assertNotNull(expected);
    assertEquals(expected.toString(), "La data non è valida");
  }

  @Test
  public void shouldReturnReleaseDate() {
    ReleaseDate releaseDate = new ReleaseDate("2007-01-19");
    assertEquals("2007-01-19", releaseDate.getValue());
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(ReleaseDate year1, ReleaseDate year2) {
    assertEquals(year1, year2);
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(ReleaseDate year1, ReleaseDate year2) {
    assertNotEquals(year1, year2);
  }
}
