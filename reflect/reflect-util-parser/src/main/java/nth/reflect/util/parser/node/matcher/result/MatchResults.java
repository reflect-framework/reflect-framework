package nth.reflect.util.parser.node.matcher.result;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.matcher.result.filter.AllResultsFilter;
import nth.reflect.util.parser.node.matcher.result.filter.ResultFilter;
import nth.reflect.util.parser.node.matcher.result.filter.RulesResultFilter;
import nth.reflect.util.parser.node.matcher.rule.MatchRule;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;

/**
 * {@link MatchResults} are the matchResults of a {@link NodeParser}. Each match
 * is stored in one a {@link MatchResult} per {@link MatchRule}.
 * 
 * @author nilsth
 *
 */
public class MatchResults {

	private static final AllResultsFilter All_RESULTS_FILTER = new AllResultsFilter();
	public static final MatchResults NOT_FOUND = new MatchResults(new ArrayList<>());
	private final List<MatchResult> matchResults;
	private final List<Node> nodes;

	public MatchResults(List<Node> nodes) {
		this.nodes = nodes;
		matchResults = new ArrayList<>();
	}

	public boolean hasResults() {
		return !matchResults.isEmpty();
	}

	public void add(MatchRule matchRule, int index) {
		if (!hasResults()) {
			createAndAddNewResult(index, matchRule);
		} else {
			MatchResult lastResult = getLastResult();
			if (matchRule == lastResult.getRule()) {
				lastResult.setLastNodeIndex(index);
			} else {
				createAndAddNewResult(index, matchRule);
			}
		}
	}

	private void createAndAddNewResult(int index, MatchRule matchRule) {
		MatchResult newResult = new MatchResult(matchRule, index);
		matchResults.add(newResult);
	}

	public int getFirstNodeIdex(ResultFilter resultFilter) {
		throwErrorWhenNothingWasFound();
		int first = resultFilter.getFirstNodeIndex(matchResults);
		return first;
	}

	private void throwErrorWhenNothingWasFound() {
		if (!hasResults()) {
			throw new NoResultsFoundException();
		}
	}

	public int getLastNodeIndex(ResultFilter resultFilter) {
		throwErrorWhenNothingWasFound();
		int last = resultFilter.getLastNodeIndex(matchResults);
		return last;
	}

	public void replaceFoundNodesWith(Node replacementNode) {
		removeFoundNodes();
		int replacementIndex = getFirstNodeIndex();
		nodes.add(replacementIndex, replacementNode);
	}

	public void removeFoundNodes() {
		throwErrorWhenNothingWasFound();
		int firstIndex = getFirstNodeIndex();
		int lastIndex = getLastNodeIndex();

		for (int index = firstIndex; index <= lastIndex; index++) {
			nodes.remove(firstIndex);
		}
	}

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();
		reply.append(MatchResults.class.getSimpleName());
		if (hasResults()) {
			reply.append("\n");
			for (MatchResult matchResult : matchResults) {
				reply.append("  " + matchResult + "\n");
			}

		} else {
			reply.append(": NOTHING FOUND!");
		}
		return reply.toString();
	}

	public List<Node> getFoundNodes(ResultFilter resultFilter) {
		List<Node> found = resultFilter.getFoundNodes(matchResults, nodes);
		return found;
	}
	
	public List<Node> getFoundNodes(MatchRules rulesToFind) {
		RulesResultFilter resultFilter = new RulesResultFilter(rulesToFind);
		return getFoundNodes(resultFilter);
	}

	public List<Node> getFoundNodes() {
		return getFoundNodes(All_RESULTS_FILTER);
	}

	public MatchResult getLastResult() {
		throwErrorWhenNothingWasFound();
		return matchResults.get(matchResults.size() - 1);
	}

	public MatchResult getFirstResult() {
		throwErrorWhenNothingWasFound();
		return matchResults.get(0);
	}

	public int getFirstNodeIndex() {
		throwErrorWhenNothingWasFound();
		int lastNodeIndex = getFirstResult().getFirstNodeIndex();
		return lastNodeIndex;
	}

	public int getLastNodeIndex() {
		throwErrorWhenNothingWasFound();
		int lastNodeIndex = getLastResult().getLastNodeIndex();
		return lastNodeIndex;
	}

	public int getNumberOfMatches(MatchRule ruleToFind) {
		int numberOfMatches = (int) matchResults.stream().filter(r -> r.getRule() == ruleToFind).count();
		return numberOfMatches;
	}

	public List<Node> getNodes() {
		return nodes;
	}


}
