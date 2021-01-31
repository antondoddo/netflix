package com.antondoddo.production.valueobject;

public final class NullSeason implements Season {

  @Override
  public int getValue() {
    return 0;
  }

  @Override
  public String toString() {
    return "No season";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    NullSeason season = (NullSeason) o;
    return this.getValue() == season.getValue();
  }
}
