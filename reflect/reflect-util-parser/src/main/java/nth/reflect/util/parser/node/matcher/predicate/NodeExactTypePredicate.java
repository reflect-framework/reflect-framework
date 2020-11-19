package nth.reflect.util.parser.node.matcher.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;

public class NodeExactTypePredicate implements Predicate<Node> {

	private final List<Class<? extends Node>> exactNodeTypesToFind;

	@SafeVarargs
	public NodeExactTypePredicate(Class<? extends Node>... exactNodeTypesToFind) {
		this.exactNodeTypesToFind = Arrays.asList( exactNodeTypesToFind);
	}

	@Override
	public boolean test(Node node) {
		boolean matches = exactNodeTypesToFind.contains(node.getClass());
		return matches;
	}

}
