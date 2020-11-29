package com.antondoddo.guardabili;

import java.util.ArrayList;
import java.util.UUID;

public final class Movie {

	private UUID idFilm;
	private Titolo titolo;
	private FilmDuration durataFilm;
	private final ArrayList<Genere> generi;
	private Regista regista;
	private final ArrayList<Attore> cast;
	private Descrizione descrizione;
	private AnnoPubblicazione annoPublicazione;

	public UUID getIdFilm() {
		return idFilm;
	}

	public Titolo getTitolo() {
		return titolo;
	}

	public FilmDuration getDurataFilm() {
		return durataFilm;
	}

	

	public Regista getRegista() {
		return regista;
	}

	public ArrayList<Attore> getCast() {
		return cast;
	}

	public Descrizione getDescrizione() {
		return descrizione;
	}

	public AnnoPubblicazione getAnnoPublicazione() {
		return annoPublicazione;
	}

	public Movie(UUID idFilm, Titolo titolo, FilmDuration durataFilm, ArrayList<Genere> generi, Regista regista,
			ArrayList<Attore> cast, Descrizione descrizione, AnnoPubblicazione annoPublicazione) {

		this.idFilm = idFilm;
		this.titolo = titolo;
		this.durataFilm = durataFilm;
		this.generi = generi;
		this.regista = regista;
		this.cast = cast;
		this.descrizione = descrizione;
		this.annoPublicazione = annoPublicazione;
	}

	@Override
	public String toString() {
		return "Movie [idFilm=" + idFilm + ", titolo=" + titolo + ", durataFilm=" + durataFilm + ", generi=" + generi
				+ ", regista=" + regista + ", cast=" + cast + ", descrizione=" + descrizione
				+ ", annoPublicazione=" + " annoPublicazione]";
	}

}
