package com.partkyle.prefix.nodes;

import java.util.Set;
import java.util.TreeSet;

public abstract class BasicNodeImpl implements Node, Comparable<BasicNodeImpl> {

	private Set<Node> children = new TreeSet<Node>();

	@Override
	public Set<Node> getChildren() {
		return children;
	}

	@Override
	public void addChild(Node child) {
		children.add(child);
	}

	public void prefix(Visitor visitor) {
		prefix(this, visitor);
	}

	private void prefix(Node n, Visitor visitor) {
		visitor.visit(n);

		for (Node child : n.getChildren()) {
			prefix(child, visitor);
		}
	}

	@Override
	public int compareTo(BasicNodeImpl o) {
		if (o == null)
			return 1;

		if (o.getPrecedence() <= getPrecedence())
			return -1;
		else
			return 1;

	}
}
