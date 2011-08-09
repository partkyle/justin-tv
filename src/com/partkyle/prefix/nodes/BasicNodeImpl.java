package com.partkyle.prefix.nodes;


public abstract class BasicNodeImpl implements Node {

	private Node leftChild;
	private Node rightChild;

	@Override
	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	@Override
	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public void addChild(Node node) {
		if (leftChild == null) {
			leftChild = node;
		} else if (rightChild == null) {
			rightChild = node;
		} else if (rightChild.getPrecedence() < node.getPrecedence()) {
			node.addChild(rightChild);
			rightChild = node;
		} else {
			throw new RuntimeException("Too many children");
		}
	}

	public void prefix(Visitor visitor) {
		prefix(this, visitor);
	}

	private void prefix(Node n, Visitor visitor) {
		visitor.visit(n);
		if (leftChild != null)
			prefix(leftChild, visitor);
		if (rightChild != null)
			prefix(rightChild, visitor);
	}

}
