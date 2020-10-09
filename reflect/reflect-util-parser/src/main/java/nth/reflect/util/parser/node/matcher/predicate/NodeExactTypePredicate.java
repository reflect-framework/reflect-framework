package nth.reflect.util.parser.node.matcher.predicate;

import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;

public class NodeExactTypePredicate implements Predicate<Node> {

	private final Class<? extends Node> exactNodeTypeToFind;

	public NodeExactTypePredicate(Class<? extends Node> exactNodeTypeToFind) {
		this.exactNodeTypeToFind = exactNodeTypeToFind;
	}

	@Override
	public boolean test(Node node) {
		boolean matches = exactNodeTypeToFind==node.getClass();
		return matches;
	}

}
