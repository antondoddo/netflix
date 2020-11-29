package com.antondoddo.guardabili;

public class Titolo {

	private String titoloFilm;

	public String getTitoloFilm() {
		return titoloFilm;
	}

	public Titolo(String titoloFilm) throws IllegalTitleException {

		if (titoloFilm.length() == 0 || titoloFilm.length() > 80) {

			throw new IllegalTitleException();
			
		}
		
		this.titoloFilm = titoloFilm;
	}

}
