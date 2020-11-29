package com.antondoddo.guardabili;

import java.time.Duration;

public class FilmDuration {

	private Duration durataFilm;

	public Duration getDurataFilm() {
		return durataFilm;
	}

	public FilmDuration(Duration durataFilm) throws FilmDurationException {

		if (!checkDurata(durataFilm)) {

			throw new FilmDurationException();

		}

		this.durataFilm = durataFilm;

	}

	public boolean checkDurata(Duration durata) {

		// 24 ore
		Duration max = Duration.ofSeconds(86400);
		Duration min = Duration.ofSeconds(1);

		return durata.getSeconds() > min.getSeconds() && durata.getSeconds() < max.getSeconds();

	}

}
