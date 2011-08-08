package com.partkyle.prefix.nodes;

public class FactorNode extends BasicNodeImpl {
	private String factor;

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
	public int getPrecedence() {
		return 0;
	}

	@Override
	public String toString() {
		return factor;
	}

}
