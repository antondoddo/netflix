package com.antondoddo.valueobjects;

import com.antondoddo.exception.InvalidNameOrSurnameException;

public class Director {

	private String name;
	private String surname;

	public Director(String name, String surname) throws InvalidNameOrSurnameException {
		if (!(checkLength(name) && checkLength(surname))) {
			throw new InvalidNameOrSurnameException();
		}
		this.name = name;
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public boolean checkLength(String name) {

		return name.length() > 2;
	}
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		Director filmDirector = (Director) o;
		return this.name == filmDirector.name && this.surname == filmDirector.surname;
	}
}
