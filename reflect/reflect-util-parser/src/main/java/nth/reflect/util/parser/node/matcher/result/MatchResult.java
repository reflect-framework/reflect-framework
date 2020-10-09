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

	private final int nodeIndex;
	private  MatchRule matchRule;

	public MatchResult( int nodeIndex, MatchRule matchRule) {
		this.nodeIndex = nodeIndex;
		this.matchRule = matchRule;
	}

	public int getNodeIndex() {
		return nodeIndex;
	}
 
	public MatchRule getMatchRule() {
		return matchRule;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder().setSeperator(", ");
		title.append(" nodeIndex: ", nodeIndex)//
		.append(" matchRule=", matchRule);
		return title.toString();
	}

	public void setMatchRule(MatchRule matchRule) {
		this.matchRule=matchRule;		
	}
}
