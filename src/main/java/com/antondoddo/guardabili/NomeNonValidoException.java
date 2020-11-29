package com.antondoddo.guardabili;

public class NomeNonValidoException extends IllegalArgumentException {

	
	private static final long serialVersionUID = 1L;
	
	
	public NomeNonValidoException() {
		
		super("NomeNonValidoException");
	}


	@Override
	public String toString() {
		return getMessage() + ": Il nome o il cognome non sono validi";
	}


	
	

}
