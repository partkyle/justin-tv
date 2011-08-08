package com.partkyle.prefix.nodes;

import java.util.ArrayList;
import java.util.List;

import com.partkyle.prefix.Operator;

public class OperatorNode implements Node {
	private Operator operator;
	private List<Node> children = new ArrayList<Node>();

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
	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	@Override
	public int getPrecedence() {
		return operator.getPrecedence();
	}

	@Override
	public void addChild(Node child) {
		children.add(child);
	}
}
