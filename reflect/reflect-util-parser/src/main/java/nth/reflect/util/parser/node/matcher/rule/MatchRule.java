package nth.reflect.util.parser.node.matcher.rule;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.result.MatchResults;

/**
 * {@link MatchRules} is a fluent interface for defining a {@link List} of
 * {@link MatchRule}s for a {@link NodeMatcher}. It is much like (heavily
 * inspired) a regular expression. A {@link MatchRule} contains information such
 * as {@link #predicate} to match nodes, {@link #repetition} to indicate how
 * many times the rule should match the nodes and a {@link #parent} so that the
 * a part of the {@link MatchResults} can be filtered to get specific
 * {@link Node}s
 * 
 * @author nilsth
 *
 */
public class MatchRule {
	private final Predicate<Node> predicate;
	private final Repetition repetition;
	private Optional<MatchRules> parent;

	public MatchRule(Predicate<Node> predicate, Repetition repetition) {
		this.parent = Optional.empty();
		this.predicate = predicate;
		this.repetition = repetition;
	}

	public MatchRule(MatchRules parent, Predicate<Node> predicate, Repetition repetition) {
		this.parent = Optional.of(parent);
		this.predicate = predicate;
		this.repetition = repetition;
	}

	public Predicate<Node> getPredicate() {
		return predicate;
	}

	public Repetition getRepetition() {
		return repetition;
	}

	public Optional<MatchRules> getParent() {
		return parent;
	}

	public void setParent(MatchRules parent) {
		this.parent = Optional.of(parent);
	}

	public boolean isValid(Node child) {
		boolean isValid = predicate.test(child);
		return isValid;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder();
		title.append(MatchRule.class.getSimpleName());
		title.append(" predicate=", predicate);
		title.append(", repetition=", repetition);
		return title.toString();
	}

	public boolean mustGoToNext(MatchResults matchResults) {
		if (repetition.max == Integer.MAX_VALUE) {
			return false;
		}
		int numberOfMatches = matchResults.getNumberOfMatches(this);
		boolean mustGoToNextPattern = numberOfMatches >= repetition.getMax();
		return mustGoToNextPattern;
	}

	public boolean canGoToNext(MatchResults matchResults) {
		int numberOfMatches = matchResults.getNumberOfMatches(this);
		boolean canGoToNextPattern = numberOfMatches >= repetition.getMin();
		return canGoToNextPattern;
	}

}
