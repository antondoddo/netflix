package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.antondoddo.production.valueobject.exception.IllegalNameOrSurnameException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public final class DirectorTest {

  @Test
  public void shouldThrowIllegalDirectorException() {
    IllegalNameOrSurnameException expected = null;
    try {
      new Director("", "");
    } catch (IllegalNameOrSurnameException e) {
      expected = e;
    }
    assertNotNull(expected);
    assertEquals(expected.toString(), "Director: Il nome o il cognome non sono validi");
  }

  protected static Object[] shouldBeEqualsData() {
    Director director0 = new Director("Bella", "Pete");
    return new Object[]{
            new Object[]{
                    new Director("Damiano", "Petrungaro"),
                    new Director("Damiano", "Petrungaro"),
            },
            new Object[]{
                    director0, director0
            },
    };
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(Director director1, Director director2) {
    assertEquals(director1, director2);
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
            new Object[]{
                    new Director("Damiano", "Petrungaro"),
                    new Director("Antonio", "Farina"),
            },
    };
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(Director director1, Director director2) {
    assertNotEquals(director1, director2);
  }

  @Test
  public void shouldReturnConstructorName() {
    Director director = new Director("Antonio", "Farina");
    assertEquals("Antonio", director.getName());
  }

  @Test
  public void shouldReturnConstructorSurname() {
    Director director = new Director("Antonio", "Farina");
    assertEquals("Farina", director.getSurname());
  }
}
