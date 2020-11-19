package nth.reflect.util.parser.node.matcher.result;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.matcher.result.filter.AllResultsFilter;
import nth.reflect.util.parser.node.matcher.result.filter.ResultFilter;
import nth.reflect.util.parser.node.matcher.result.filter.RulesResultFilter;
import nth.reflect.util.parser.node.matcher.rule.MatchRule;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;

/**
 * {@link MatchResults} are the matchResults of a {@link NodeParser}. 
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

	public void add(int nodeIndex, MatchRule matchRule) {
		MatchResult newResult = new MatchResult(nodeIndex, matchRule);
		matchResults.add(newResult);
	}

	public int getFirstNodeIdex(ResultFilter resultFilter) {
		throwErrorWhenNothingWasFound();
		int first = resultFilter.getFirstNodeIndex(matchResults);
		return first;
	}
	public int getLastNodeIndex(ResultFilter resultFilter) {
		throwErrorWhenNothingWasFound();
		int last = resultFilter.getLastNodeIndex(matchResults);
		return last;
	}

	private void throwErrorWhenNothingWasFound() {
		if (!hasResults()) {
			throw new NoResultsFoundException();
		}
	}


	private void throwErrorWhenRulesWhereNotFound(List<MatchResult> results) {
		if (results.isEmpty()) {
			throw new NoRulesFoundException();
		}
	}


	public void replaceFoundNodesWith(Node replacementNode) {
		throwErrorWhenNothingWasFound();
		int firstIndex = getFirstResult().getNodeIndex();
		int lastIndex = getLastResult().getNodeIndex();
		removeFoundNodes(firstIndex,lastIndex);
		nodes.add(firstIndex, replacementNode);
	}
	
	public void replaceFoundNodesWith(Predicate<Node> predicateToFind,
			Node replacementNode) {
		throwErrorWhenNothingWasFound();
		List<Node> foundNodes = getFoundNodes(predicateToFind);//!Assuming these are all connected in nodes!!!
		int firstIndex = nodes.indexOf(foundNodes.get(0));
		int lastIndex = nodes.indexOf(foundNodes.get(foundNodes.size()-1));
		removeFoundNodes(firstIndex,lastIndex);
		nodes.add(firstIndex, replacementNode);		
	}


	public void removeFoundNodes(int firstIndex, int lastIndex) {
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


	public MatchResult getFirstResult() {
		throwErrorWhenNothingWasFound();
		return matchResults.get(0);
	}
	
	public MatchResult getFirstResult(MatchRules rulesToFind) {
		throwErrorWhenNothingWasFound();
		List<MatchResult> results = getMatchResults(rulesToFind);
		throwErrorWhenRulesWhereNotFound(results);
		return results.get(0);
	}

	private List<MatchResult> getMatchResults(MatchRules rulesToFind) {
		List<MatchResult> resuts = matchResults.stream().filter(r-> r.getMatchRule().getSources().contains(rulesToFind)).collect(Collectors.toList());
		return resuts;
	}

	public MatchResult getLastResult() {
		throwErrorWhenNothingWasFound();
		return matchResults.get(matchResults.size() - 1);
	}

	
	public MatchResult getLastResult(MatchRules rulesToFind) {
		throwErrorWhenNothingWasFound();
		List<MatchResult> results = getMatchResults(rulesToFind);
		throwErrorWhenRulesWhereNotFound(results);
		return results.get(results.size()-1);
	}


	public void remove(MatchResult matchResult) {
		matchResults.remove(matchResult);		
	}

	public int getNumberOfMatches(MatchRule ruleToFind) {
		int numberOfMatches = (int) matchResults.stream().filter(r -> r.getMatchRule() == ruleToFind).count();
		return numberOfMatches;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public List<Node> getFoundNodes( Predicate<? super Node> predicateToFind) {
		List<Node> found=new ArrayList<>();
		List<MatchResult> results = matchResults.stream().filter(result -> result.getMatchRule().getPredicate().equals(predicateToFind)).collect(Collectors.toList());
		for (MatchResult matchResult : results) {
			int nodeIndex=matchResult.getNodeIndex();
			found.add(nodes.get(nodeIndex));
		}
		return found;
	}

	public boolean hasFoundRuleWithPredicate(Predicate<Node> predicateToFind) {
		boolean foundRuleWithGivenPredicate = matchResults.stream().anyMatch(result -> result.getMatchRule().getPredicate()==predicateToFind);
		return 	foundRuleWithGivenPredicate;
	}

	public MatchResults getMatchResultsWithoutLastResult() {
		MatchResults reply=new MatchResults(nodes);
		for (int i=0;i<matchResults.size()-1;i++) {
			MatchResult matchResult=matchResults.get(i);
			reply.add(matchResult.getNodeIndex(), matchResult.getMatchRule());
		}
		return reply;
	}




}
