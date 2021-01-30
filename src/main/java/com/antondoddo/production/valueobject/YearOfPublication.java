package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalYearOfPublicationException;
import java.util.regex.Pattern;

public final class YearOfPublication {

  private static final Pattern pat = Pattern.compile(
          "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])");
  private final String yearPublication;

  public YearOfPublication(String yearPublication) throws IllegalYearOfPublicationException {

    if (!checkData(yearPublication)) {

      throw new IllegalYearOfPublicationException();
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
    YearOfPublication publication = (YearOfPublication) o;
    return this.yearPublication == publication.yearPublication;
  }
}
