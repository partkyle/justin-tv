package com.partkyle.prefix.nodes;

public interface Node {

	Node getLeftChild();

	Node getRightChild();

	int getPrecedence();
	
	void addChild(Node node);

	public void prefix(Visitor visitor);
}
