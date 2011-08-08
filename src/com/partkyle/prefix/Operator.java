package com.partkyle.prefix;

public enum Operator {
	Add("+", 2),
	Subtract("-", 2),
	Multiply("*", 3),
	Divide("/", 3),
	OpenParen("(", 6),
	CloseParen(")", 6),
	Factor();

	String character = "";
	int precedence = 0;

	Operator() {

	}

	Operator(String character, int precedence) {
		this.character = character;
		this.precedence = precedence;
	}

	public static Operator fromToken(String token) {
		for (Operator op : values()) {
			if (op.character.equals(token)) {
				return op;
			}
		}
		return Factor;
	}

	public String getCharacter() {
		return character;
	}

	public int getPrecedence() {
		return precedence;
	}

}
