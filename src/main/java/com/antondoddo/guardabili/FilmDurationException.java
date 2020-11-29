package com.antondoddo.guardabili;

public class FilmDurationException extends IllegalArgumentException {

	
	private static final long serialVersionUID = 1L;

	public FilmDurationException() {
		
		super("FilmDurationException");
	}

	@Override
	public String toString() {
		return getMessage() +": Il film è troppo corto o troppo lungo";
	}
	
	
	

}
