package nth.reflect.util.parser.node.matcher;

import java.util.Optional;

import nth.reflect.util.parser.node.matcher.rule.MatchRule;

/**
 * Combines {@link MatchRule} and the number of repetitions to create a
 * {@link Pattern}.
 * <p>
 * pattern:<br>
 * ABC(1..*)D(1..*)<br>
 * To match:<br>
 * ABCCCDEF<br>
 * Patterns that are sequentially tested:<br>
 * ABCCCCCD<br>
 * ABCCCCDD<br>
 * ABCCCDDD<br>
 * ABCCCDD<br>
 * ABCCCD<br>
 * 
 */

//TODO: 
//- Get SysmacUtil tests to run
//- change MatchRule.parents to matchRule.Sources(List<object>), to include predecate<Node> or MatchRules
//- Get all reflect-util-parser and Sysmac util tests to run

public class PatternPart {

	private MatchRule matchRule;
	private int repetition;
	/**
	 * {@link #temporaryRepetition} is used for eager rules with a high max
	 * repetition {@link Integer#MAX_VALUE}. A {@link #temporaryRepetition} will be
	 * calculated based on the numbers of nodes to be matched minus
	 * {@link PatternParts#getNrOfRequiredRepetions}. The
	 * {@link #temporaryRepetition} is made empty when the {@link #repetition} is
	 * modified. {@link #repetition} is from then on used instead.
	 */
	private Optional<Integer> temporaryRepetition;

	public PatternPart(MatchRule matchRule) {
		this.matchRule = matchRule;
		if (matchRule.getRepetitionRule().isEager()) {
			repetition = matchRule.getRepetitionRule().getMax();
			temporaryRepetition = Optional.of(0);
		} else {
			repetition = matchRule.getRepetitionRule().getMin();
			temporaryRepetition = Optional.empty();
		}
	}

	public MatchRule getMatchRule() {
		return matchRule;
	}

	public void setMatchRule(MatchRule matchRule) {
		this.matchRule = matchRule;
	}

	public int getRepetition() {
		if (temporaryRepetition.isPresent()) {
			return temporaryRepetition.get();
		} else {
			return repetition;
		}
	}

	public void setTemporaryRepetition(int repetition) {
		if (!isWithinRepetitionBounds(repetition)) {
			throw new RuntimeException("Temporary repetition out of bounds");
		}
		temporaryRepetition = Optional.of(repetition);
	}

	private boolean isWithinRepetitionBounds(int repetition) {
		return repetition >= matchRule.getRepetitionRule().getMin()
				&& repetition <= matchRule.getRepetitionRule().getMax();
	}

	public boolean hasTemporaryRepetition() {
		return temporaryRepetition.isPresent();
	}

	public boolean canModifyRepetition() {
		if (matchRule.getRepetitionRule().isEager()) {
			return getRepetition() > matchRule.getRepetitionRule().getMin();
		} else {
			return getRepetition() < matchRule.getRepetitionRule().getMax();
		}
	}

	public void modifyRepetition() {
		if (matchRule.getRepetitionRule().isEager()) {
			repetition = getRepetition() - 1;
			temporaryRepetition = Optional.empty();
		} else {
			repetition = getRepetition() + 1;
			temporaryRepetition = Optional.empty();
		}
	}

	public int getMinimumRepetition() {
		return matchRule.getRepetitionRule().getMin();
	}

	public int getMaximumRepetition() {
		return matchRule.getRepetitionRule().getMax();
	}

}
