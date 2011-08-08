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
			result = getRawInput("\n> ");
			converter.convert(result);
		}
	}

	public String convert(String result) {
		List<String> output = new ArrayList<String>();
		Stack<Operator> operators = new Stack<Operator>();
		List<String> tokens = getTokens(result);
		for (String token : tokens) {
			Operator op = Operator.fromToken(token);
			if (op != null) {
				if (operators.size() > 0 && operators.peek() != null) {
					if (op.precedence > operators.peek().precedence) {
						operators.add(operators.size() - 1, op);
					} else if (op.precedence <= operators.peek().precedence) {
						output.add(operators.pop().character);
						operators.add(op);
					}
				} else {
					operators.add(op);
				}
			} else {
				output.add(token);
			}
		}

		output.add(operators.pop().character);

		System.out.println(operators);

		Collections.reverse(output);

		for (String string : output) {
			System.out.print(string);
			System.out.print(" ");
		}
		System.out.println();
		return "";
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
