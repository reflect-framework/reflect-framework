package nth.reflect.util.parser.node.matcher;

import java.util.List;
import java.util.regex.Matcher;

import com.google.common.base.Optional;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.FirstMatchRule;
import nth.reflect.util.parser.node.matcher.rule.LastMatchRule;
import nth.reflect.util.parser.node.matcher.rule.MatchRule;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;

/**
 * A {@link NodeMatcher} tries to match a list of {@link Node}s with a list of
 * {@link MatchRules}, much like (heavily inspired by) the Regex
 * {@link Matcher}. The results are returned in {@link MatchResults}
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

	private MatchResults match(List<Node> nodes, int nodeStartIndex) {
		MatchResults matchResults = new MatchResults(nodes);

		int ruleIndex = 0;

		for (int nodeIndex = nodeStartIndex; nodeIndex < nodes.size(); nodeIndex++) {
			Node node = nodes.get(nodeIndex);
			Optional<MatchRule> foundRule = findMatchingRule(node, ruleIndex, matchResults);
			if (foundRule.isPresent()) {
				MatchRule matchRule = foundRule.get();
				matchResults.add(matchRule, nodeIndex);
				ruleIndex = matchRules.indexOf(matchRule);
			} else {
				// found first mismatch but this is ok if we where done anyway
				return getValidatedResults(matchResults);
			}
		}
		// done matching. did we match all?
		return getValidatedResults(matchResults);
	}


	private MatchResults getValidatedResults(MatchResults matchResults) {
		if (done(matchResults)) {
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
		int firstFoundNodeIndex = matchResults.getFirstNodeIndex();
		boolean firstFoundNodeIsFirst = firstFoundNodeIndex == 0;
		boolean firstRuleOk = firstMatchCanBeAnyNode() || firstFoundNodeIsFirst;

		int lastFoundNodeIndex = matchResults.getLastResult().getLastNodeIndex();
		int lastNodeIndex = matchResults.getNodes().size() - 1;
		boolean lastFoundNodeisLast = lastFoundNodeIndex == lastNodeIndex;
		boolean lastRuleOk = lastMatchCanBeAnyNode() || lastFoundNodeisLast;

		return firstRuleOk && lastRuleOk;
	}

	private boolean lastMatchCanBeAnyNode() {
		return matchRules.getLastMatchRule() == LastMatchRule.CAN_BE_ANY_NODE;
	}

	private boolean firstMatchCanBeAnyNode() {
		return matchRules.getFirstMatchRule() == FirstMatchRule.CAN_BE_ANY_NODE;
	}

	private boolean done(MatchResults matchResults) {
		if (!matchResults.hasResults()) {
			return false;
		}
		int ruleIndex = matchRules.indexOf(matchResults.getLastResult().getRule());
		if (passedAllRules(ruleIndex)) {
			return true;
		}
		if (remainingRulesCanGoToNext(ruleIndex + 1, matchResults)) {
			return true;
		}
		return false;
	}

	private boolean remainingRulesCanGoToNext(int ruleIndex, MatchResults matchResults) {
		if (ruleIndex >= matchRules.size()) {
			// is last rule so remaining matchRules can go to next
			return true;
		}
		MatchRule matchRule = matchRules.get(ruleIndex);
		boolean canGoToNext = matchRule.canGoToNext(matchResults);
		if (canGoToNext) {
			// recursive call for the remaining node matchRules;
			boolean remainingRulesCanGoToNext = remainingRulesCanGoToNext(ruleIndex + 1, matchResults);
			return remainingRulesCanGoToNext;
		}
		return false;
	}

	private boolean passedAllRules(int ruleIndex) {
		boolean passedAllRules = ruleIndex >= matchRules.size();
		return passedAllRules;
	}

	private Optional<MatchRule> findMatchingRule(Node node, int startRuleIndex, MatchResults matchResults) {
		int ruleCurrentIndex = findRuleThatMustMatch(startRuleIndex, matchResults);

		Optional<MatchRule> found = findMatchingRule(node, startRuleIndex, ruleCurrentIndex, matchResults);

		return found;
	}

	private Optional<MatchRule> findMatchingRule(Node node, int ruleStartIndex, int ruleCurrentIndex, MatchResults matchResults) {
		MatchRule matchRule = matchRules.get(ruleCurrentIndex);
		if (matchRule.getPredicate().test(node)) {
			return Optional.of(matchRule);
		} else {
			if (ruleCurrentIndex - 1 < ruleStartIndex) {
				// nothing found if we are back where we started
				return Optional.absent();
			} else {
				// try to find a matching rule in the previous matchRules (recursively)
				return findMatchingRule(node, ruleStartIndex, ruleCurrentIndex - 1, matchResults);
			}
		}
	}

	private int findRuleThatMustMatch(int ruleIndex, MatchResults matchResults) {
		if (ruleIndex >= matchRules.size()) {
			return matchRules.size() - 1;
		}
		MatchRule matchRule = matchRules.get(ruleIndex);
		if (matchRule.mustGoToNext(matchResults) || matchRule.canGoToNext(matchResults)) {
			// recursive call
			int findNextRuleIndexToMatch = findRuleThatMustMatch(ruleIndex + 1, matchResults);
			return findNextRuleIndexToMatch;
		} else {
			return ruleIndex;
		}
	}

}
