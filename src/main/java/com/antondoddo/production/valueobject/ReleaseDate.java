package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalReleaseDateException;
import java.util.regex.Pattern;

public final class ReleaseDate {

  private static final Pattern pat = Pattern.compile(
          "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])");
  private final String yearPublication;

  public ReleaseDate(String yearPublication) throws IllegalReleaseDateException {

    if (!checkData(yearPublication)) {

      throw new IllegalReleaseDateException();
    }
    this.yearPublication = yearPublication;
  }

  public String getValue() {
    return this.yearPublication;
  }

  private boolean checkData(String dataPub) {

    return pat.matcher(dataPub).matches();
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
    return this.yearPublication == publication.yearPublication;
  }
}