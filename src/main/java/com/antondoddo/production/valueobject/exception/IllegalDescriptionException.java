package com.antondoddo.production.valueobject.exception;

import com.antondoddo.production.valueobject.Description;

public class IllegalDescriptionException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;
	
	Description description = new Description("");
	
	public IllegalDescriptionException() {
		super("Descrizione non valida");
	}
	@Override
	public String toString() {
		return getMessage() + ": la descrizione deve contenere più di " + description.getMin() + " caratteri ma meno di " + description.getMax();
	}
}
