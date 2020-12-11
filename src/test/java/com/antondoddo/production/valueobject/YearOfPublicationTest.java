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

  protected static Object[] shouldBeThrowIllegalYearOfPublicationExceptionData() {
    return new Object[]{
        new Object[]{
            20201
        },
        new Object[]{
            20
        },
    };
  }

  protected static Object[] shouldBeEqualsData() {
    YearOfPublication yearOfPublication = new YearOfPublication(1998);
    return new Object[]{
        new Object[]{
            new YearOfPublication(1993),
            new YearOfPublication(1993),
        },
        new Object[]{
            yearOfPublication,
            yearOfPublication
        }
    };
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
        new Object[]{
            new YearOfPublication(1993),
            new YearOfPublication(1994),
        },
    };
  }

  @Test
  @Parameters(method = "shouldBeThrowIllegalYearOfPublicationExceptionData")
  public void shouldBeThrowIllegalYearOfPublicationException(Integer value) {
    IllegalYearOfPublicationException expected = null;
    try {
      new YearOfPublication(value);
    } catch (IllegalYearOfPublicationException ex) {
      expected = ex;
    }
    assertNotNull(expected);
    assertEquals(expected.toString(), "La data non è valida");
  }

  @Test
  public void shouldReturnYearOfPublication() {
    YearOfPublication yearOfPublication = new YearOfPublication(2007);
    assertEquals(Integer.valueOf(2007), yearOfPublication.getValue());
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(YearOfPublication year1, YearOfPublication year2) {
    assertEquals(year1, year2);
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(YearOfPublication year1, YearOfPublication year2) {
    assertNotEquals(year1, year2);
  }
}
