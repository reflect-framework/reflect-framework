package nth.reflect.util.parser.node.matcher.result.filter;

import java.util.List;

import nth.reflect.util.parser.node.matcher.result.MatchResult;

public class AllResultsFilter extends ResultFilter {

	
	@Override
	public int getFirstNodeIndex(List<MatchResult> matchResults) {
		throwErrorWhenNoResultsAreFound(matchResults);
		MatchResult firstResult = matchResults.get(0);
		int firstNodeIndex=firstResult.getNodeIndex();
		return firstNodeIndex;
	}

	@Override
	public int getLastNodeIndex(List<MatchResult> matchResults) {
		throwErrorWhenNoResultsAreFound(matchResults);
		MatchResult lastResult=matchResults.get(matchResults.size()-1);
		int lastNodeIndex=lastResult.getNodeIndex();
		return lastNodeIndex;
	}


}
