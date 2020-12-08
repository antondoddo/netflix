package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.antondoddo.production.valueobject.exception.IllegalYearOfPublicationException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class YearOfPublicationTest {

  protected static Object[] shouldBeThrowIllegalYearOfPublicationException() {
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
  @Test
  @Parameters(method = "shouldBeThrowIllegalYearOfPublicationException")
  public void shouldBeThrowIllegalYearOfPublication(String dateString) {
    IllegalYearOfPublicationException expected = null;
    try {
      YearOfPublication yearOfPublication = new YearOfPublication(dateString);
    } catch (IllegalYearOfPublicationException ex) {
      expected = ex;
    }
    assertNotNull(expected);
    assertEquals(expected.toString(), "DataException: La data non è valida");
  }

  @Test
  public void shouldReturnYearOfPublication() {
    YearOfPublication yearOfPublication = new YearOfPublication("2007-01-19");
    assertEquals("2007-01-19", yearOfPublication.getYearOfPublication());
  }

  protected static Object[] shouldBeEqualsData() {
    YearOfPublication yearOfPublication = new YearOfPublication("1998-03-27");
    return new Object[]{
            new Object[]{
                    new YearOfPublication("1993-01-19"),
                    new YearOfPublication("1993-01-19"),
            },
            new Object[]{
                    yearOfPublication,
                    yearOfPublication
            }
    };
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(YearOfPublication year1, YearOfPublication year2) {
    assertEquals(year1, year2);
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
            new Object[]{
                    new YearOfPublication("1993-01-19"),
                    new YearOfPublication("1994-02-21"),
            },
    };
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(YearOfPublication year1, YearOfPublication year2) {
    assertNotEquals(year1, year2);
  }
}
