package com.partkyle.prefix.parser;

import java.util.Stack;

import com.partkyle.prefix.Operator;
import com.partkyle.prefix.nodes.FactorNode;
import com.partkyle.prefix.nodes.Node;
import com.partkyle.prefix.nodes.OperatorNode;

public class InfixParser {

	public Node parse(Scanner scanner) {
		Node currentNode = null;
		Stack<Node> stack = new Stack<Node>();

		for (String token : scanner.getTokens()) {
			Operator tokenType = Operator.fromToken(token);
			Node node = null;
			switch (tokenType) {
			case Add:
			case Subtract:
			case Multiply:
			case Divide:
			case OpenParen:
			case CloseParen:
				node = new OperatorNode(tokenType);
				break;

			case Factor:
				node = new FactorNode(token);
				break;

			default:
				break;
			}

			if (stack.size() == 0) {
				stack.add(node);
			} else {
				Node current = stack.peek();

				if (current.getPrecedence() < node.getPrecedence()) {
					node.addChild(stack.pop());
					currentNode = node;
					stack.add(node);
				} else {
					current.addChild(node);
				}
			}
		}

		return stack.pop();
	}
}