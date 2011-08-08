package com.partkyle.prefix.nodes;

import java.util.List;

public class FactorNode implements Node {
	private String factor;
	private List<Node> children;

	public FactorNode(String factor) {
		this.factor = factor;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
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
		return 0;
	}
}
