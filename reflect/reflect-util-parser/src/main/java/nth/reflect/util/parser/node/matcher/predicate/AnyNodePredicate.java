package nth.reflect.util.parser.node.matcher.predicate;

import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;

public class AnyNodePredicate implements Predicate<Node> {

	@Override
	public boolean test(Node node) {
		return true;
	}

}
