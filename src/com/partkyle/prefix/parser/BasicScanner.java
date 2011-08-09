package com.partkyle.prefix.parser;

import java.util.Arrays;
import java.util.List;

public class BasicScanner implements Scanner {
	String expression;

	public BasicScanner(String expression) {
		this.expression = expression;
	}

	@Override
	public List<String> getTokens() {
		return Arrays.asList(expression.split(" "));
	}

}
