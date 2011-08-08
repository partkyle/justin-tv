package com.partkyle.prefix.parser;

import java.util.Stack;

import com.partkyle.prefix.Operator;
import com.partkyle.prefix.nodes.FactorNode;
import com.partkyle.prefix.nodes.Node;
import com.partkyle.prefix.nodes.OperatorNode;

public class InfixParser {

	public Node parse(Scanner scanner) {
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
				Node current = stack.pop();

				if (current.getPrecedence() < node.getPrecedence()) {
					node.addChild(current);
					current = node;
				} else {
					current.addChild(node);
				}

				stack.add(current);
			}
		}

		return stack.get(0);
	}
}