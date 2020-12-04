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

}
