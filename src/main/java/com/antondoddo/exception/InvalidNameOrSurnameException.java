package com.antondoddo.exception;

public class InvalidNameOrSurnameException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidNameOrSurnameException() {
		super("NomeNonValidoException");
	}
	@Override
	public String toString() {
		return getMessage() + ": Il nome o il cognome non sono validi";
	}
}
