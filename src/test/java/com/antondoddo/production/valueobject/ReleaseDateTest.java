package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.antondoddo.production.valueobject.exception.IllegalReleaseDateException;
import java.time.LocalDate;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ReleaseDateTest {

  protected static Object[] shouldBeThrowIllegalReleaseDateException() {
    return new Object[]{
        new Object[]{
            "1600-01-01"
        },
    };
  }

  protected static Object[] shouldBeEqualsData() {
    ReleaseDate releaseDate = new ReleaseDate(LocalDate.parse("1998-03-27"));
    return new Object[]{
        new Object[]{
            new ReleaseDate(LocalDate.parse("1993-01-19")),
            new ReleaseDate(LocalDate.parse("1993-01-19")),
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
            new ReleaseDate(LocalDate.parse("1993-01-19")),
            new ReleaseDate(LocalDate.parse("1994-02-21")),
        },
    };
  }

  @Test
  @Parameters(method = "shouldBeThrowIllegalReleaseDateException")
  public void shouldBeThrowIllegalReleaseDate(String dateString) {
    IllegalReleaseDateException expected = null;
    try {
      ReleaseDate releaseDate = new ReleaseDate(LocalDate.parse(dateString));
    } catch (IllegalReleaseDateException ex) {
      expected = ex;
    }
    assertNotNull(expected);
    assertEquals(expected.toString(), "La data non è valida");
  }

  @Test
  public void shouldReturnReleaseDate() {
    ReleaseDate releaseDate = new ReleaseDate(LocalDate.parse("2007-01-19"));
    assertEquals("2007-01-19", releaseDate.getValue().toString());
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(ReleaseDate releaseDate1, ReleaseDate releaseDate2) {
    assertEquals(releaseDate1, releaseDate2);
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(ReleaseDate releaseDate1, ReleaseDate releaseDate2) {
    assertNotEquals(releaseDate1, releaseDate2);
  }
}
