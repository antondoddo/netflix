package com.antondoddo.production.valueobject;


import com.antondoddo.production.valueobject.exception.IllegalDurationException;

public final class Duration {

  private final java.time.Duration timeDuration;

  public Duration(java.time.Duration timeDuration) throws IllegalDurationException {
    if (!checkDuration(timeDuration)) {
      throw new IllegalDurationException();
    }
    this.timeDuration = timeDuration;
  }

  public java.time.Duration getTimeDuration() {
    return timeDuration;
  }

  private boolean checkDuration(java.time.Duration duration) {
    java.time.Duration max = java.time.Duration.ofSeconds(86400);  //24 hours
    java.time.Duration min = java.time.Duration.ofSeconds(1);
    return duration.getSeconds() > min.getSeconds() && duration.getSeconds() < max.getSeconds();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Duration duration = (Duration) o;
    return this.timeDuration == duration.timeDuration;
  }
}
