package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalTitleException;

public class Title {

  private final String value;

  public Title(String title) throws IllegalTitleException {
    if (title.length() == 0 || title.length() > 80) {
      throw new IllegalTitleException();
    }
    this.value = title;
  }

  public String getTitoloFilm() {
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
    Title annoPublicazione = (Title) o;
    return this.value == annoPublicazione.value;
  }
}
