package nth.reflect.util.parser.node.matcher.predicate;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;

public class NodeTypeAndMatchChildrenPredicate extends NodeTypePredicate {

	private final NodeMatcher nodeMatcher;

	public NodeTypeAndMatchChildrenPredicate(Class<? extends Node> nodeType, MatchRules matchRules) {
		super(nodeType);
		nodeMatcher = new NodeMatcher(matchRules);
	}

	@Override
	public boolean test(Node node) {
		boolean typeOk = super.test(node);
		if (!typeOk) {
			return false;
		} else {
			List<Node> nodes = node.getNodes();
			MatchResults matchResults = nodeMatcher.match(nodes);
			boolean hasResults = matchResults.hasResults();
			return hasResults;
		}
	}

}
