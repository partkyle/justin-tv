package com.partkyle.prefix.expression;

public class ArithmeticExpression implements Expression {
	private Expression left;
	private Operator operator;
	private Expression right;

	public ArithmeticExpression(Expression left, Operator operator, Expression right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}

	public String toInfix() {
		return String.format("(%s %s %s)", left, operator, right);
	}

	public String toPrefix() {
		return String.format("(%s %s %s)", operator, left, right);
	}

	@Override
	public String toString() {
		return toPrefix();
	}

}
