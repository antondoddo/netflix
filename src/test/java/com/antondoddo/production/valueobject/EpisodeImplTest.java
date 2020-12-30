package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.antondoddo.production.valueobject.exception.IllegalEpisodeException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class EpisodeImplTest {

  protected static Object[] shouldBeThrowIllegalEpisodeExceptionData() {
    return new Object[]{
        new Object[]{
            0
        },
        new Object[]{
            -1
        },
        new Object[]{
            1001
        },
    };
  }

  @Test
  @Parameters(method = "shouldBeThrowIllegalEpisodeExceptionData")
  public void shouldBeThrowIllegalEpisodeException(int value) {
    IllegalEpisodeException expected = null;
    try {
      EpisodeImpl episode = new EpisodeImpl(value);
    } catch (IllegalEpisodeException ex) {
      expected = ex;
    }
    assertNotNull(expected);
    assertEquals(expected.toString(), "Numero dell'episodio non valido,"
        + " può essere compreso solo tra 1 e 1000");
  }

  @Test
  public void shouldBeReturnValue() {
    EpisodeImpl episode = new EpisodeImpl(3);
    assertEquals(3, episode.getValue());
  }

  @Test
  public void shouldReturnToString() {
    EpisodeImpl episode = new EpisodeImpl(1);
    assertEquals("Episodio numero " + 1, episode.toString());
  }

  protected static Object[] shouldBeEqualsData() {
    EpisodeImpl ep = new EpisodeImpl(2);
    return new Object[]{
        new Object[]{
            ep,
            ep
        },
        new Object[]{
            new EpisodeImpl(1),
            new EpisodeImpl(1),
        }
    };
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(EpisodeImpl ep1, EpisodeImpl ep2) {
    assertEquals(ep1, ep2);
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
        new EpisodeImpl(2),
        new EpisodeImpl(3),
    };
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(EpisodeImpl ep1, EpisodeImpl ep2) {
    assertNotEquals(ep1, ep2);
  }
}
