package com.antondoddo.production.valueobject;

import org.junit.Test;

import com.antondoddo.production.valueobject.exception.IllegalNameOrSurnameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DirectorTest {

	@Test
	public void shouldThrowIllegalDirectorException() {

		IllegalNameOrSurnameException expected = null;

		try {
			Director director = new Director("", "");

		} catch (IllegalNameOrSurnameException e) {

			expected = e;

		}

		assertNotNull(expected);

		assertEquals(expected.toString(), "Director: Il nome o il cognome non sono validi");
	}

	@Test
	public void shouldReturnConstructorName() {

		Director director = new Director("Antonio", "Farina");

		assertEquals("Antonio",director.getName());

	}
	
	@Test
	public void shouldReturnConstructorSurname() {

		Director director = new Director("Antonio", "Farina");

		assertEquals("Farina",director.getSurname());

	}

}
