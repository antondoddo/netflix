package com.antondoddo.production.valueobject;

public class NullEpisode implements Episode {

  @Override
  public Integer getValue() {
    return 0;
  }

  @Override
  public String toString() {
    return "No episode";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NullEpisode episode = (NullEpisode) o;
    return this.getValue() == episode.getValue();
  }
}
