package nth.reflect.util.parser.node.matcher.result.filter;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.matcher.result.MatchResult;
import nth.reflect.util.parser.node.matcher.result.NoResultsFoundException;

public abstract class ResultFilter {

	public abstract int getFirstNodeIndex(List<MatchResult> matchResults);

	public abstract int getLastNodeIndex(List<MatchResult> matchResults);

	public List<Node> getFoundNodes(List<MatchResult> matchResults, List<Node> nodes) {
		throwErrorWhenNoResultsAreFound(matchResults);
		int first = getFirstNodeIndex(matchResults);
		int last = getLastNodeIndex(matchResults);
		List<Node> found = new ArrayList<>();
		for (int index = first; index <= last; index++) {
			Node child = nodes.get(index);
			found.add(child);
		}
		return found;
	}

	public void throwErrorWhenNoResultsAreFound(List<MatchResult> matchResults) {
		if (matchResults.isEmpty()) {
			throw new NoResultsFoundException();
		}
	}

}
