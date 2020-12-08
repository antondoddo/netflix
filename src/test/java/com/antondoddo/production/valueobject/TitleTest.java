package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.antondoddo.production.valueobject.exception.IllegalTitleException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TitleTest {

  protected static Object[] shouldBeThrowIllegalTitleExceptionData() {
    return new Object[]{
            new Object[]{
                    "",
            }, new Object[]{
            "aoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
                    + "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
                    + "oooooooooooooooooooooooooooooooooooooooooooooooooooooo"
    }
    };
  }

  @Test
  @Parameters(method = "shouldBeThrowIllegalTitleExceptionData")
  public void shouldBeThrowIllegalTitleException(String value) {
    IllegalTitleException expected = null;
    try {
      Title title = new Title(value);
    } catch (IllegalTitleException ex) {
      expected = ex;
    }
    assertNotNull(expected);
    assertEquals(expected.toString(), "Invalid Title :Il titolo inserito non è valido");
  }

  @Test
  public void shouldReturnTitle() {
    Title title = new Title("Il dragone");
    assertEquals("Il dragone", title.getTitle());
  }

  protected static Object[] shouldBeEqualsData() {
    Title title = new Title("Spazio profondo");
    return new Object[]{
            new Object[]{
                    new Title("Odissea nello spazio"),
                    new Title("Odissea nello spazio")
            },
            new Object[]{
                    title,
                    title
            }
    };
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(Title title1, Title title2) {
    assertEquals(title1, title2);
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
            new Object[]{
                    new Title("Odissea nello spazio"),
                    new Title("Scontro tra titani")
            },
    };
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(Title title1, Title title2) {
    assertNotEquals(title1, title2);
  }
}