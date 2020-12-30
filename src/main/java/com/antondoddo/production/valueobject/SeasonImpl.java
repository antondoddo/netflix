package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalSeasonException;

public final class SeasonImpl implements Season {

  private final int value;

  public SeasonImpl(int value) throws IllegalSeasonException {
    if (value < 1 || value > 50) {
      throw new IllegalSeasonException();
    }
    this.value = value;
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return "Stagione " + value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    SeasonImpl season = (SeasonImpl) o;
    return this.getValue() == season.getValue();
  }
}
