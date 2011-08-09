package com.partkyle.prefix.parser;

import java.util.Stack;

public class Parser {
	
	private Scanner scanner;
	
	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public String parse() {
		Stack<String> factors = new Stack<String>();
		Stack<String> operators = new Stack<String>();
		for (String token : scanner.getTokens()) {
			if (isOperator(token)) {
				while (!operators.isEmpty()) {
					if (getPrecedence(token) <= getPrecedence(operators.peek())) {
						String right = factors.pop(), left = factors.pop();
						factors.push(eval(left, operators.pop(), right));
					} else {
						break;
					}
				}
				operators.push(token);
			} else if ("(".equals(token)) {
				operators.push(token);
			} else if (")".equals(token)) {
				while (!operators.isEmpty()) {
					String operator = operators.pop();
					if ("(".equals(operator)) {
						break;
					} else {
						String right = factors.pop(), left = factors.pop();
						factors.push(eval(left, operator, right));
					}
				}
			} else {
				factors.push(token);
			}
		}

		while (!operators.isEmpty()) {
			String right = factors.pop(), left = factors.pop();
			factors.push(eval(left, operators.pop(), right));
		}

		System.out.println(factors.pop());
		return factors.pop();
	}

	private String eval(String left, String operator, String right) {
		return String.format("(%s %s %s)", operator, left, right);
	}

	private boolean isOperator(String token) {
		return "+-*/".indexOf(token) != -1;
	}

	private int getPrecedence(String token) {
		if ("*".equals(token) || "/".equals(token)) {
			return 2;
		} else if ("+".equals(token) || "-".equals(token)) {
			return 1;
		}
		return 0;
	}
}
