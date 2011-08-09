package com.partkyle.prefix.parser;

import java.util.Stack;

import com.partkyle.prefix.expression.Expression;
import com.partkyle.prefix.expression.Factor;
import com.partkyle.prefix.expression.LeftRightExpression;
import com.partkyle.prefix.expression.Operator;

public class InfixParser {

	private Scanner scanner;

	public InfixParser(Scanner scanner) {
		this.scanner = scanner;
	}

	public Expression parse() {
		Stack<Expression> factors = new Stack<Expression>();
		Stack<String> operators = new Stack<String>();
		for (String token : scanner.getTokens()) {
			if (isOperator(token)) {
				while (!operators.isEmpty()) {
					if (getPrecedence(token) <= getPrecedence(operators.peek())) {
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
				while (!operators.isEmpty()) {
					String operator = operators.pop();
					if ("(".equals(operator)) {
						break;
					} else {
						Expression right = factors.pop(), left = factors.pop();
						factors.push(eval(left, operator, right));
					}
				}
			} else {
				factors.push(new Factor(token));
			}
		}

		while (!operators.isEmpty()) {
			Expression right = factors.pop(), left = factors.pop();
			factors.push(eval(left, operators.pop(), right));
		}

		return factors.pop();
	}

	private Expression eval(Expression left, String operator, Expression right) {
		return new LeftRightExpression(left, Operator.fromToken(operator), right);
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
