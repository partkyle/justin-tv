package com.partkyle.prefix.nodes;

import java.util.Collection;

public interface Node {
	Collection<Node> getChildren();

	int getPrecedence();

	public void addChild(Node child);

	public void prefix(Visitor visitor);
}
