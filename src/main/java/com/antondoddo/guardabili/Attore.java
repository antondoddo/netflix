package com.antondoddo.guardabili;

public class Attore {

	private String nome;
	private String cognome;

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public Attore(String nome, String cognome) throws NomeNonValidoException {

		if (!(checkLength(nome) && checkLength(cognome))) {

			throw new NomeNonValidoException();

		}
		this.nome = nome;
		this.cognome = cognome;

	}

	public boolean checkLength(String nome) {

		return nome.length()>2;
	}

}
