package nth.reflect.util.parser.node.matcher;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.matcher.result.MatchResult;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.FirstMatchRule;
import nth.reflect.util.parser.node.matcher.rule.LastMatchRule;
import nth.reflect.util.parser.node.matcher.rule.MatchRule;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;

/**
 * A {@link NodeMatcher} tries to match a list of {@link Node}s with a list of
 * {@link MatchRules}, much like (heavily inspired by) the Regex
 * {@link Matcher}. The results are returned in {@link MatchResults}
 * <p>
 * It uses a backtracking algorithm (e.g. see
 * a<href="https://www.baeldung.com/java-sudoku">https://www.baeldung.com/java-sudoku</a>)
 * <p>
 * if a {@link Node} matches the first {@link MatchRule} the algorithm moves to
 * the next node repeats the process for the next node.
 * <p>
 * if this node does not match the {@link MatchRule} is will try the next
 * {@link MatchRule} (if {@link MatchRule#canGoToNext(MatchResults)}. It will
 * travel back to the previous node and try the next MatchRule for that node.
 * <p>
 * It will return the {@link MatchResult} if there are no more
 * {@link MatchRules}.
 * <p>
 * 
 * @author nilsth
 *
 */
public class NodeMatcher {

	private final MatchRules matchRules;

	public NodeMatcher(MatchRules matchRules) {
		this.matchRules = matchRules;
	}

	public MatchResults match(List<Node> nodes) {
		if (firstMatchCanBeAnyNode()) {
			MatchResults matchResults = matchFromAnyChild(nodes);
			return matchResults;
		} else {
			MatchResults matchResults = matchFromFirstChild(nodes);
			return matchResults;
		}
	}

	private MatchResults matchFromAnyChild(List<Node> nodeChildren) {
		for (int nodeStartIndex = 0; nodeStartIndex < nodeChildren.size(); nodeStartIndex++) {
			MatchResults matchResults = match(nodeChildren, nodeStartIndex);
			if (matchResults.hasResults()) {
				return matchResults;
			}
		}
		return MatchResults.NOT_FOUND;
	}

	private MatchResults matchFromFirstChild(List<Node> nodes) {
		int startIndex = 0;
		MatchResults matchResults = match(nodes, startIndex);
		return matchResults;
	}

	private MatchResults match(List<Node> nodes, int startNodeIndex) {
		Pattern pattern = new Pattern(matchRules, nodes, startNodeIndex);
		MatchResults matchResults = pattern.match();
		if (matchResults.hasResults()) {
			if (firstAndLastRulesAreOk(matchResults)) {
				return matchResults;
			} else {
				return MatchResults.NOT_FOUND;
			}
		} else {
			return MatchResults.NOT_FOUND;
		}
	}

	private boolean firstAndLastRulesAreOk(MatchResults matchResults) {
		int firstFoundNodeIndex = matchResults.getFirstResult().getNodeIndex();
		boolean firstFoundNodeIsFirst = firstFoundNodeIndex == 0;
		boolean firstRuleOk = firstMatchCanBeAnyNode() || firstFoundNodeIsFirst;

		int lastFoundNodeIndex = matchResults.getLastResult().getNodeIndex();
		int lastNodeIndex = matchResults.getNodes().size() - 1;
		boolean lastFoundNodeIsLast = lastFoundNodeIndex == lastNodeIndex;
		boolean lastRuleOk = lastMatchCanBeAnyNode() || lastFoundNodeIsLast;

		return firstRuleOk && lastRuleOk;
	}

	private boolean lastMatchCanBeAnyNode() {
		return matchRules.getLastMatchRule() == LastMatchRule.CAN_BE_ANY_NODE;
	}

	private boolean firstMatchCanBeAnyNode() {
		return matchRules.getFirstMatchRule() == FirstMatchRule.CAN_BE_ANY_NODE;
	}

}
