package nth.reflect.util.parser.node.matcher.predicate;

import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;

public class NodeTypePredicate implements Predicate<Node> {

	private final Class<? extends Node> nodeType;

	public NodeTypePredicate(Class<? extends Node> nodeType) {
		this.nodeType = nodeType;
	}

	@Override
	public boolean test(Node node) {
		boolean matches = nodeType.isInstance(node);
		return matches;
	}

}
