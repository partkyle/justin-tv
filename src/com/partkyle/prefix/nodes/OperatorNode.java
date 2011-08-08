package com.partkyle.prefix.nodes;

import com.partkyle.prefix.Operator;

public class OperatorNode extends BasicNodeImpl {
	private Operator operator;

	public OperatorNode(Operator operator) {
		this.operator = operator;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	@Override
	public int getPrecedence() {
		return operator.getPrecedence();
	}

	@Override
	public String toString() {
		return operator.getCharacter();
	}
}
