package nth.reflect.util.parser.node.matcher.result;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.matcher.rule.MatchRule;

/**
 * {@link MatchResults} are the matchResults of a {@link NodeParser}. Each match
 * is stored in one a {@link MatchResult} per {@link MatchRule}. It contains the
 * {@link #matchRule}, {@link #firstNodeIndex} and the {@link #lastNodeIndex}
 * 
 * @author nilsth
 *
 */
public class MatchResult {

	private final int firstNodeIndex;
	private int lastNodeIndex;
	private final MatchRule matchRule;

	public MatchResult(MatchRule matchRule, int index) {
		this.matchRule = matchRule;
		this.firstNodeIndex = index;
		this.lastNodeIndex = index;
	}

	public int getFirstNodeIndex() {
		return firstNodeIndex;
	}

	public int getLastNodeIndex() {
		return lastNodeIndex;
	}

	public void setLastNodeIndex(int last) {
		this.lastNodeIndex = last;
	}

	public MatchRule getRule() {
		return matchRule;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder().setSeperator(", ");
		if (matchRule.getParent().isPresent()) {
			title.append(" parentId=", matchRule.getParent().get().hashCode());
		}
		title.append(" matchRule=", matchRule).append(", firstNodeIndex: ", firstNodeIndex).append(", last: ",
				lastNodeIndex);
		return title.toString();
	}
}
