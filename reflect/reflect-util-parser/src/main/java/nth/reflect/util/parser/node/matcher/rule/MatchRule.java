package nth.reflect.util.parser.node.matcher.rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
 * many times the rule should match the nodes and a {@link #sources} so that the
 * a part of the {@link MatchResults} can be filtered to get specific
 * {@link Node}s
 * 
 * @author nilsth
 *
 */
public class MatchRule {
	private final Predicate<Node> predicate;
	private final Repetition repetition;
	private Set<Object> sources;

	public MatchRule(Predicate<Node> predicate, Repetition repetition) {
		this.sources = new HashSet<>();
		sources.add(predicate);
		this.predicate = predicate;
		this.repetition = repetition;
	}

	public MatchRule(MatchRules parent, Predicate<Node> predicate, Repetition repetition) {
		this.sources = new HashSet<>();
		this.predicate = predicate;
		this.repetition = repetition;
	}

	public Predicate<Node> getPredicate() {
		return predicate;
	}

	public Repetition getRepetitionRule() {
		return repetition;
	}

	public Set<Object> getSources() {
		return sources;
	}

	public void setSources(Set<Object> sources) {
		this.sources = sources;
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

	
}
