package com.partkyle.prefix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		List<String> output = new ArrayList<String>();
		Stack<String> operators = new Stack<String>();
		for (String token : getTokens(result)) {
			if (isOperator(token)) {
				while (!operators.isEmpty()) {
					String operator = operators.peek();
					if (isOperator(operator) && getPrecedence(token) <= getPrecedence(operator)) {
						output.add(operators.pop());
					} else {
						break;
					}
				}
				operators.add(token);
			} else if ("(".equals(token)) {
				operators.add(token);
			} else if (")".equals(token)) {
				String s;
				while (!(s = operators.pop()).equals("(")) {
					output.add(s);
				}
			} else {
				output.add(token);
			}
		}

		while (!operators.isEmpty()) {
			output.add(operators.pop());
		}

		Collections.reverse(output);

		for (String string : output) {
			System.out.print(string);
			System.out.print(" ");
		}
		System.out.println();
		return "";
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
