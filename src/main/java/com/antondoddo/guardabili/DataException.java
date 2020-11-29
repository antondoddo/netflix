package com.antondoddo.guardabili;

public class DataException extends IllegalArgumentException {

	
	private static final long serialVersionUID = 1L;
	
	public DataException() {
		
		super("DataException:");
	}

	@Override
	public String toString() {
		
		return getMessage() + ": La data non è valida";
	}
	
	

}
