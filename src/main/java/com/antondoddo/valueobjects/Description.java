package com.antondoddo.valueobjects;

import com.antondoddo.exception.InvalidDescriptionException;

public class Description {
	
	private String value;
	private int min= 10;
	private int max = 200;
	
	public Description(String content) throws InvalidDescriptionException {
		if (!(content.length() > min && content.length() < max)) {
			throw new InvalidDescriptionException();
		}
		this.value = content;
	}
	public String getValue() {
		return value;
	}
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		Description description = (Description) o;
		return this.value == description.value;
	}
}