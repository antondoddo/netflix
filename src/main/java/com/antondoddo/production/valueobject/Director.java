package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalNameOrSurnameException;

public final class Director {

  private final String name;
  private final String surname;

  public Director(String name, String surname) throws IllegalNameOrSurnameException {
    if (!(checkLength(name) && checkLength(surname))) {
      throw new IllegalNameOrSurnameException("Director");
    }
    this.name = name;
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  private boolean checkLength(String name) {

    return name.length() > 2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Director filmDirector = (Director) o;
    return this.name == filmDirector.name && this.surname == filmDirector.surname;
  }
}
