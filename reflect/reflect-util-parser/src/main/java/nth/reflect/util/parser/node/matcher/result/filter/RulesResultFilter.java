package nth.reflect.util.parser.node.matcher.result.filter;

import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.matcher.result.MatchResult;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;

public class RulesResultFilter extends ResultFilter {

	private final MatchRules rulesToFind;

	public RulesResultFilter(MatchRules rulesToFind) {
		this.rulesToFind = rulesToFind;
	}
	
	@Override
	public int getFirstNodeIndex(List<MatchResult> matchResults) {
		throwErrorWhenNoResultsAreFound(matchResults);
		List<MatchResult> filteredResults = findResults(matchResults);
		MatchResult firstResult = filteredResults.get(0);
		int firstNodeIndex=firstResult.getNodeIndex();
		return firstNodeIndex;
	}

	@Override
	public int getLastNodeIndex(List<MatchResult> matchResults) {
		throwErrorWhenNoResultsAreFound(matchResults);
		List<MatchResult> filteredResults = findResults(matchResults);
		MatchResult lastResult=filteredResults.get(filteredResults.size()-1);
		int lastNodeIndex=lastResult.getNodeIndex();
		return lastNodeIndex;
	}

	private List<MatchResult> findResults(List<MatchResult> matchResults) {
		return matchResults.stream().filter(result-> result.getMatchRule().getSources().contains(rulesToFind)).collect(Collectors.toList());
	}

}
