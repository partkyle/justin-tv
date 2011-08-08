package com.partkyle.prefix;

public enum Operator {
	Add("+", 2),
	Subtract("-", 2),
	Multiply("*", 3),
	Divide("/", 3),
	OpenParen("(", 6),
	CloseParen(")", 6);

	String character;
	int precedence;

	Operator(String character, int precedence) {
		this.character = character;
		this.precedence = precedence;
	}

	public boolean isFaster(Operator op) {
		return precedence > op.precedence;
	}

	public static Operator fromToken(String token) {
		for (Operator op : values()) {
			if (op.character.equals(token)) {
				return op;
			}
		}
		return null;
	}

}
