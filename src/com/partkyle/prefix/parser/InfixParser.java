package com.partkyle.prefix.parser;

import java.util.Stack;

import com.partkyle.prefix.expression.Expression;
import com.partkyle.prefix.expression.Factor;
import com.partkyle.prefix.expression.ArithmeticExpression;
import com.partkyle.prefix.expression.Operator;

public class InfixParser {

	private Scanner scanner;

	public InfixParser(Scanner scanner) {
		this.scanner = scanner;
	}

	public Expression parse() {
		try {
			Stack<Expression> factors = new Stack<Expression>();
			Stack<String> operators = new Stack<String>();
			for (String token : scanner.getTokens()) {
				if (isOperator(token)) {
					while (!operators.isEmpty()) {
						if (getPrecedence(token) <= getPrecedence(operators.peek())) {
							if (factors.size() < 2) {
								throw new MalformedExpressionException("No factors for opertator " + operators.peek());
							}
							Expression right = factors.pop(), left = factors.pop();
							factors.push(eval(left, operators.pop(), right));
						} else {
							break;
						}
					}
					operators.push(token);
				} else if ("(".equals(token)) {
					operators.push(token);
				} else if (")".equals(token)) {
					boolean parensMatch = false;
					while (!operators.isEmpty()) {
						String operator = operators.pop();
						if ("(".equals(operator)) {
							parensMatch = true;
							break;
						} else {
							Expression right = factors.pop(), left = factors.pop();
							factors.push(eval(left, operator, right));
						}

					}
					if (!parensMatch) {
						throw new MalformedExpressionException("Missing open \"(\"");
					}
				} else {
					factors.push(new Factor(token));
				}
			}

			while (!operators.isEmpty()) {
				String operator = operators.pop();
				if (isOperator(operator)) {
					if (factors.size() < 2) {
						throw new MalformedExpressionException("No factors for opertator " + operators.peek());
					}
					Expression right = factors.pop(), left = factors.pop();
					factors.push(eval(left, operator, right));
				} else {
					throw new MalformedExpressionException("Error near: " + operator);
				}
			}

			return factors.pop();

		} catch (MalformedExpressionException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	private Expression eval(Expression left, String operator, Expression right) {
		return new ArithmeticExpression(left, Operator.fromToken(operator), right);
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

	public class MalformedExpressionException extends Exception {
		private static final long serialVersionUID = 1L;

		public MalformedExpressionException() {
			super();
		}

		public MalformedExpressionException(String message) {
			super(message);
		}
	}
}
