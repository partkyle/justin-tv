package com.partkyle.prefix.nodes;

import java.util.List;

public interface Node {
	List<Node> getChildren();

	int getPrecedence();
}
