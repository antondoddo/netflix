package com.antondoddo.valueobjects;


import com.antondoddo.exception.FilmDurationException;

public class Duration {

	private java.time.Duration filmDuration;

	public Duration(java.time.Duration filmDuration) throws FilmDurationException {
		if (!checkDuration(filmDuration)) {
			throw new FilmDurationException();
		}

		this.filmDuration = filmDuration;
	}
	public java.time.Duration getDurataFilm() {
		return filmDuration;
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
        return this.filmDuration == duration.filmDuration;
    }
}
