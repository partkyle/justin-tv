package com.partkyle.prefix.nodes;

public class ConsoleVisitor implements Visitor {

	@Override
	public void visit(Node n) {
		System.out.println(n);
	}

}
