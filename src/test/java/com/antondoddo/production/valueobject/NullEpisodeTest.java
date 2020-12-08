package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class NullEpisodeTest {

  @Test
  public void shouldReturnValue() {
    NullEpisode nullEpisode = new NullEpisode();
    assertEquals(0, nullEpisode.getValue());
  }

  @Test
  public void shouldReturnANullEpisodeString() {
    NullEpisode nullEpisode = new NullEpisode();
    assertEquals("No episode", nullEpisode.toString());
  }
}
