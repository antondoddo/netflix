package com.antondoddo.guardabili;

public class Descrizione {

	private String contenuto;

	public String getContenuto() {
		return contenuto;
	}

	public Descrizione(String contenuto) throws ContenutoNonValidoException {

		if (!(contenuto.length() > 10 && contenuto.length() < 200)) {

			throw new ContenutoNonValidoException();

		}

		this.contenuto = contenuto;

	}

}
