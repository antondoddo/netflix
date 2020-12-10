package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalDescriptionException;

public final class Description {

  public static final int min = 10;
  public static final int max = 200;
  private final String value;

  public Description(String value) throws IllegalDescriptionException {
    if (!(value.length() > min && value.length() < max)) {
      throw new IllegalDescriptionException();
    }
    this.value = value;
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
    Description description = (Description) o;
    return this.value == description.value;
  }
}