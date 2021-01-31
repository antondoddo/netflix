package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalReleaseDateException;
import java.time.LocalDate;

public final class ReleaseDate {

  private final LocalDate date;

  public ReleaseDate(LocalDate date) throws IllegalReleaseDateException {

    if (!checkData(date)) {

      throw new IllegalReleaseDateException();
    }
    this.date = date;
  }

  public LocalDate getValue() {
    return this.date;
  }

  private boolean checkData(LocalDate date) {

    return !date.isBefore(LocalDate.of(1700, 1, 1));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReleaseDate publication = (ReleaseDate) o;
    return this.date.equals(publication.date);
  }
}
