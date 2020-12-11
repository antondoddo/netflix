package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalEpisodeException;

public final class RealEpisode implements Episode {

  private final Integer value;

  public RealEpisode(Integer value) {

    if (value <= 0) {
      throw new IllegalEpisodeException();
    }
    this.value = value;
  }

  @Override
  public Integer getValue() {
    return this.value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RealEpisode episode = (RealEpisode) o;
    return this.getValue().equals(episode.getValue());
  }
}
