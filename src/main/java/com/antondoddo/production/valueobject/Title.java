package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalTitleException;

public final class Title {

  private final String value;

  public Title(String title) throws IllegalTitleException {
    if (title.length() == 0 || title.length() > 80) {
      throw new IllegalTitleException();
    }
    this.value = title;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Title releaseDate = (Title) o;
    return this.value == releaseDate.value;
  }
}
