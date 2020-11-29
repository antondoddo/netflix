package com.antondoddo.guardabili;

public class ContenutoNonValidoException extends IllegalArgumentException {

	
	private static final long serialVersionUID = 1L;
	
	
	public ContenutoNonValidoException() {
		
		super("Contenuto non valido");
	}


	@Override
	public String toString() {
		return getMessage() + ": la descrizione deve contenere più di 10 caratteri ma meno di 200";
	}
	
	

}
