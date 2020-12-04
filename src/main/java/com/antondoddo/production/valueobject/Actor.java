package com.antondoddo.production.valueobject;

import com.antondoddo.production.valueobject.exception.IllegalNameOrSurnameException;

public class Actor {

	private String name;
	private String surname;
	
	public Actor(String name, String surname) throws IllegalNameOrSurnameException {
		if (!(checkLength(name) && checkLength(surname))) {
			throw new IllegalNameOrSurnameException("Actor");
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
		return name.length()>2;
	}
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		Actor actor = (Actor) o;
		return this.name == actor.name && this.surname == actor.surname;
	}
}
