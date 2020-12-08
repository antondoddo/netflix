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
public final class ActorTest {

  @Test
  public void shouldThrowIllegalNameOrSurnameException() {

    IllegalNameOrSurnameException expected = null;
    try {
      new Actor("A", "B");

    } catch (IllegalNameOrSurnameException e) {

      expected = e;

    }
    assertNotNull(expected);
    assertEquals(expected.toString(), "Actor: Il nome o il cognome non sono validi");
  }

  protected static Object[] shouldBeEqualsData() {

    Actor actor1 = new Actor("Bella", "Pelui");

    return new Object[]{
            new Object[]{
                    new Actor("Alessandra", "Conti"),
                    new Actor("Alessandra", "Conti"),
            },
            new Object[]{

                    actor1,
                    actor1
            },
    };
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(Actor actor1, Actor actor2) {

    assertEquals(actor1, actor2);
  }

  protected static Object[] shouldBeNotEqualsData() {

    return new Object[]{
            new Object[]{
                    new Actor("Damieno", "Petrunghero"),
                    new Actor("Goffredo", "Goffredi"),
            },
    };
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(Actor actor1, Actor actor2) {

    assertNotEquals(actor1, actor2);
  }

  @Test
  public void shouldReturnConstructorName() {
    Actor actor1 = new Actor("Alessandra", "Conti");
    assertEquals("Alessandra", actor1.getName());
  }

  @Test
  public void shouldReturnConstructorSurname() {
    Actor actor0 = new Actor("Alessandra", "Conti");
    assertEquals("Conti", actor0.getSurname());
  }




}
