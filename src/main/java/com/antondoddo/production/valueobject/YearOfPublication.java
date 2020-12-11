package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalYearOfPublicationException;

public final class YearOfPublication {

  private final Integer value;

  public YearOfPublication(Integer value) throws IllegalYearOfPublicationException {

    if (!checkData(value)) {
      throw new IllegalYearOfPublicationException();
    }

    this.value = value;
  }

  public Integer getValue() {
    return this.value;
  }

  private boolean checkData(Integer value) {
    return value > 1700 && value < 10000;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    YearOfPublication publication = (YearOfPublication) o;
    return this.value.equals(publication.value);
  }
}
