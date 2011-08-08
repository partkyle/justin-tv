package com.partkyle.prefix.parser;

import com.partkyle.prefix.Operator;
import com.partkyle.prefix.nodes.FactorNode;
import com.partkyle.prefix.nodes.Node;
import com.partkyle.prefix.nodes.OperatorNode;

public class InfixParser {

	public Node parse(Scanner scanner) {
		Node currentNode = null;

		for (String token : scanner.getTokens()) {
			Operator tokenType = Operator.fromToken(token);
			Node node = null;
			switch (tokenType) {
			case Add:
			case Subtract:
			case Multiply:
			case Divide:
				node = new OperatorNode(tokenType);
				break;

			case Factor:
				node = new FactorNode(token);
				break;

			default:
				break;
			}

			if (currentNode == null) {
				currentNode = node;
			} else {
				// Compare the nodes for precedence to see where to put this new
				// node
				if (currentNode.getPrecedence() < node.getPrecedence()) {
					node.addChild(currentNode);
					currentNode = node;
				} else {
					currentNode.addChild(node);
				}
			}
		}

		return currentNode;
	}
}