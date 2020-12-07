package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.antondoddo.production.valueobject.exception.IllegalDescriptionException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public final class DescriptionTest {

  protected static Object[] shouldThrowIllegalDescriptionExceptionData() {

    return new Object[]{

            new Object[]{

                    "asd"
            },
            new Object[]{
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
            }
    };
  }

  protected static Object[] shouldBeEqualsData() {

    Description description = new Description("Un pericoloso drago volava minaccioso sul cielo in tempesta");
    return new Object[]{

            new Object[]{
                    new Description("Il cavaliere prese la spada e uccise il drago con un colpo al cuore"),
                    new Description("Il cavaliere prese la spada e uccise il drago con un colpo al cuore"),
            },
            new Object[]{

                    description, description
            },
    };
  }

  protected static Object[] shouldBeNotEqualsData() {

    return new Object[]{

            new Object[]{
                    new Description("Il cavaliere prese la spada e uccise il drago con un colpo al cuore"),
                    new Description("Il cavaliere decise di risparmiare il drago e se ne andò verso il profondo Nord"),
            },

    };
  }

  @Test
  @Parameters(method = "shouldThrowIllegalDescriptionExceptionData")
  public void shouldThrowIllegalDescriptionException(String value) {

    IllegalDescriptionException expected = null;

    try {
      Description description = new Description(value);
    } catch (IllegalDescriptionException exception) {

      expected = exception;

    }

    assertNotNull(expected);
    assertEquals(expected.toString(), ": la descrizione deve contenere più di "
            + Description.min + " caratteri ma meno di "
            + Description.max);

  }

  @Test
  public void shouldReturnConstructorValue() {

    Description description = new Description("Damiano è proprio un amico speciale!");

    assertEquals("Damiano è proprio un amico speciale!", description.getValue());

  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(Description description1, Description description2) {

    assertEquals(description1, description2);


  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(Description description1, Description description2) {

    assertNotEquals(description1, description2);


  }
}
