package com.partkyle.prefix.nodes;

public class LoggingVisitor implements Visitor {
	@Override
	public void visit(Node n) {
		System.out.print(n.toString() + " ");
	}
}
