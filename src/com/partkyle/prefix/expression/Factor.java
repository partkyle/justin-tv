package com.partkyle.prefix.expression;

public class Factor implements Expression {
	private String value;

	public Factor(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toInfix() {
		return value;
	}

	@Override
	public String toPrefix() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
