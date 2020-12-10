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
}
