package com.antondoddo.exception;

import com.antondoddo.valueobjects.Description;

public class InvalidDescriptionException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;
	
	Description description = new Description("");
	
	public InvalidDescriptionException() {
		super("Descrizione non valida");
	}
	@Override
	public String toString() {
		return getMessage() + ": la descrizione deve contenere più di " + description.getMin() + " caratteri ma meno di " + description.getMax();
	}
}
