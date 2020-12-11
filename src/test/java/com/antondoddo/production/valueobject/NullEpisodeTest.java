package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public final class NullEpisodeTest {

  @Test
  public void shouldReturnValue() {
    NullEpisode nullEpisode = new NullEpisode();
    assertEquals(Integer.valueOf(0), nullEpisode.getValue());
  }

  @Test
  public void shouldReturnNullEpisodeString() {
    NullEpisode nullEpisode = new NullEpisode();
    assertEquals("No episode", nullEpisode.toString());
  }

  protected static Object[] shouldBeEqualsData() {
    return new Object[]{
      new Object[]{
        new NullEpisode(),
        new NullEpisode()
      }
    };
  }

  @Test
  @Parameters(method = "shouldBeEqualsData")
  public void shouldBeEquals(NullEpisode episode1, NullEpisode episode2) {
    assertEquals(episode1, episode2);
  }

  protected static Object[] shouldBeNotEqualsData() {
    return new Object[]{
      new Object[]{
        new NullEpisode(),
        "No episode"
      },
      new Object[]{
        new NullEpisode(),
        0
      }
    };
  }

  @Test
  @Parameters(method = "shouldBeNotEqualsData")
  public void shouldBeNotEquals(NullEpisode episode1, Object obj) {
    assertNotEquals(episode1, obj);
  }
}
