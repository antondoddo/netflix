package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalEpisodeException;
import com.antondoddo.production.valueobject.exception.IllegalSeasonException;

public interface Episode {

  int getValue();

  String toString();

  boolean equals(Object o);
}
