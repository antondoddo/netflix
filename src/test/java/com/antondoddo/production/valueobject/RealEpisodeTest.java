package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import com.antondoddo.production.valueobject.exception.IllegalEpisodeException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RealEpisodeTest {

  protected static Object[] shouldBeThrownIllegalEpisodeExceptionData() {
    return new Object[]{
        "0",
        "-1",
    };
  }

  @Test
  @Parameters(method = "shouldBeThrownIllegalEpisodeExceptionData")
  public void shouldBeThrownIllegalEpisodeException(Integer value) {
    IllegalEpisodeException exception = null;
    try {
      new RealEpisode(value);
    } catch (IllegalEpisodeException e) {
      exception = e;
    }
    assertNotNull(exception);
    assertEquals("L'episodio non è valido", exception.toString());
  }

  protected static Object[] shouldReturnSameParameterPassedViaTheConstructorData() {
    return new Object[]{
        "10",
    };
  }

  @Test
  @Parameters(method = "shouldReturnSameParameterPassedViaTheConstructorData")
  public void shouldReturnSameParameterPassedViaTheConstructor(Integer value) {
    RealEpisode episode = new RealEpisode(value);
    assertSame(value, episode.getValue());
  }

  protected static Object[] shouldBeEqualsData() {
    RealEpisode episode = new RealEpisode(10);
    return new Object[]{
        new Object[]{new RealEpisode(10), new RealEpisode(10)},
        new Object[]{episode, episode},
    };
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(RealEpisode episode1, RealEpisode episode2) {
    assertEquals(episode1, episode2);
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
        new Object[]{new RealEpisode(10), new RealEpisode(11)},
    };
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(RealEpisode episode1, RealEpisode episode2) {
    assertNotEquals(episode1, episode2);
  }
}
