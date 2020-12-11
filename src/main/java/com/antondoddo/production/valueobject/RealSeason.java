package com.antondoddo.production.valueobject;


import com.antondoddo.production.valueobject.exception.IllegalSeasonException;

public final class RealSeason implements Season {

  private final Integer value;

  public RealSeason(Integer value) {

    if (value <= 0) {
      throw new IllegalSeasonException();
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
    RealSeason season = (RealSeason) o;
    return this.getValue().equals(season.getValue());
  }
}
