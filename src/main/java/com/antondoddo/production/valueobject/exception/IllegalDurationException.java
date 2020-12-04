package com.antondoddo.production.valueobject.exception;

public class IllegalDurationException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;

	public IllegalDurationException() {
		super("FilmDurationException");
	}
	@Override
	public String toString() {
		return getMessage() +": Il film è troppo corto o troppo lungo";
	}
}
