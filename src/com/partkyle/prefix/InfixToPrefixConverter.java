package com.partkyle.prefix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class InfixToPrefixConverter {
	public static void main(String[] args) {
		InfixToPrefixConverter converter = new InfixToPrefixConverter();
		String result = null;
		while (!"exit".equals(result)) {
			result = getRawInput("> ");
			converter.convert(result);
			System.out.println();
		}
	}

	public String convert(String result) {
		Stack<String> factors = new Stack<String>();
		Stack<String> operators = new Stack<String>();
		for (String token : getTokens(result)) {
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

		return "";
	}

	public String eval(String left, String operator, String right) {
		return String.format("(%s %s %s)", operator, left, right);
	}

	public boolean isOperator(String token) {
		return "+-*/".indexOf(token) != -1;
	}

	public int getPrecedence(String token) {
		if ("*".equals(token) || "/".equals(token)) {
			return 2;
		} else if ("+".equals(token) || "-".equals(token)) {
			return 1;
		}
		return 0;
	}

	public List<String> getTokens(String expression) {
		return Arrays.asList(expression.split("\\s+"));
	}

	public static String getRawInput(String prompt) {
		BufferedReader in;
		try {
			System.out.print(prompt);
			in = new BufferedReader(new InputStreamReader(System.in));
			String result = in.readLine();
			return result;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return "exit";
	}
}
