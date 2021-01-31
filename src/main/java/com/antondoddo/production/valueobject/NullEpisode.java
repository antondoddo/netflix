package com.antondoddo.production.valueobject;

public class NullEpisode implements Episode {

  @Override
  public int getValue() {
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
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    NullEpisode season = (NullEpisode) o;
    return this.getValue() == season.getValue();
  }
}
