package nth.reflect.util.parser.node.matcher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRule;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;

public class Pattern {

	private final MatchRules matchRules;
	private final PatternParts patternParts;
	private List<Node> nodes;
	private int startNodeIndex;
	private int nrOfNodesToMatch;

	public Pattern(MatchRules matchRules, List<Node> nodes, int startNodeIndex) {
		this.matchRules = matchRules;
		this.nodes = nodes;
		this.startNodeIndex = startNodeIndex;
		this.nrOfNodesToMatch = nodes.size() - startNodeIndex;
		this.patternParts = new PatternParts(matchRules);
		patternParts.correctRepetitions(nrOfNodesToMatch);
	}

	public Pattern(MatchRules matchRules, PatternParts patternParts, List<Node> nodes, int startNodeIndex) {
		this.matchRules = matchRules;
		this.nodes = nodes;
		this.startNodeIndex = startNodeIndex;
		this.nrOfNodesToMatch = nodes.size() - startNodeIndex;
		this.patternParts = patternParts;
		patternParts.correctRepetitions(nrOfNodesToMatch);
	}

	public MatchResults match() {
		//printForDebugging(nodes, startNodeIndex);
		
		MatchResults matchResults = new MatchResults(nodes);

		List<MatchRule> matchRules = toMatchRuleList();

		if (matchRules.size() > nrOfNodesToMatch) {
			return MatchResults.NOT_FOUND;
		}

		int nodeIndex = startNodeIndex;
		for (MatchRule matchRule : matchRules) {
			Node node = nodes.get(nodeIndex);
			if (matchRule.getPredicate().test(node)) {
				matchResults.add(nodeIndex, matchRule);
				nodeIndex++;
			} else {
				return matchNextPattern(nodes, startNodeIndex, matchRule);// recursive call
			}
		}
		// Found a match!!!
		return matchResults;

	}

	private void printForDebugging(List<Node> nodes, int startNodeIndex) {

		String nodesAsString = nodes.stream().map(n -> n instanceof TokenNode ? ((TokenNode) n).getValue() : "?")
				.collect(Collectors.joining());
		String patternAsString = toString();
		String startIndexAsString = " ".repeat(startNodeIndex);
		System.out.println(nodesAsString);
		System.out.println(startIndexAsString + patternAsString);
		System.out.println();
	}

	private MatchResults matchNextPattern(List<Node> nodes, int startNodeIndex, MatchRule matchRuleThatFailed) {
		Optional<Pattern> nextPattern = createNextPattern(matchRuleThatFailed);
		if (nextPattern.isPresent()) {
			return nextPattern.get().match();// recursive call
		} else {
			return MatchResults.NOT_FOUND;
		}
	}

	private Optional<Pattern> createNextPattern(MatchRule matchRuleThatFailed) {
		Optional<MatchRule> foundMatchRule = findMatchRuleThatCanModifyItsRepetition(matchRuleThatFailed);
		if (foundMatchRule.isPresent()) {
			MatchRule matchRule = foundMatchRule.get();
			patternParts.modify(matchRule);
			Pattern nextPattern = new Pattern(matchRules, patternParts,nodes, startNodeIndex);
			return Optional.of(nextPattern);
		}
		return Optional.empty();
	}


	private Optional<MatchRule> findMatchRuleThatCanModifyItsRepetition(MatchRule matchRule) {
		boolean canModify = patternParts.canModify(matchRule);
		if (canModify) {
			return Optional.of(matchRule);
		} else {
			return findPreviousMatchRuleThatCanModifyItsRepetition(matchRule);// recursive
		}
	}

	private Optional<MatchRule> findPreviousMatchRuleThatCanModifyItsRepetition(MatchRule matchRule) {
		int ruleIndex = matchRules.indexOf(matchRule);
		int previousRuleIndex = ruleIndex - 1;
		if (previousRuleIndex < 0) {
			return Optional.empty();
		} else {
			MatchRule previousRule = matchRules.get(previousRuleIndex);
			return findMatchRuleThatCanModifyItsRepetition(previousRule);
		}
	}

	private List<MatchRule> toMatchRuleList() {
		return patternParts.toMatchRuleList();
	}

	
	

	

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();
		List<MatchRule> matchRuleList = toMatchRuleList();
		for (MatchRule matchRule : matchRuleList) {
			reply.append(matchRule.getPredicate().toString());
		}
		return reply.toString();
	}

}
