package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalEpisodeException;

public final class EpisodeImpl implements Episode {

  private final int value;

  public EpisodeImpl(int value) throws IllegalEpisodeException {

    if (value < 1 || value > 1000) {
      throw new IllegalEpisodeException();
    }
    this.value = value;
  }

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    EpisodeImpl ep = (EpisodeImpl) o;
    return this.getValue() == ep.getValue();
  }
}
