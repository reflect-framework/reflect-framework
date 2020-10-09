package nth.reflect.util.parser.node.matcher;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.util.parser.node.matcher.rule.MatchRule;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;

/**
 * A {@link ArrayList} of {@link PatternPart}s with extra convenience constructor and methods.
 * @author nilsth
 *
 */

public class PatternParts extends ArrayList<PatternPart> {

	private static final long serialVersionUID = 8513594368547846290L;

	public PatternParts(MatchRules matchRules ) {
		for (MatchRule matchRule : matchRules) {
			PatternPart patternPart = new PatternPart(matchRule);
			add(patternPart);
		}
	}

	/**
	 * First eager {@link PatternPart}s get as many available repetitions as possible.
	 * @param nodesToMatch
	 */
	public void correctRepetitions(int nodesToMatch) {
		int knownRepetitions=getKnownRepetitions();
		int availableRepetitions=Math.max(0, nodesToMatch-knownRepetitions);
		if (availableRepetitions<=nodesToMatch) {
			for (PatternPart patternPart : this) {
				if (patternPart.hasTemporaryRepetition()) {
					int temporaryRepetition=patternPart.getMinimumRepetition()+availableRepetitions;
					if (temporaryRepetition>patternPart.getMaximumRepetition()) {
						temporaryRepetition=patternPart.getMaximumRepetition();
					}
					patternPart.setTemporaryRepetition(temporaryRepetition);
					availableRepetitions=Math.max(0,availableRepetitions-(temporaryRepetition-patternPart.getMinimumRepetition()));
				}
			}
		} else {
			//pattern is gong to fail anyway, because the pattern is longer than the nrOfNodesToMatch
		}
	}
	
	private PatternPart get(MatchRule matchRuleToFind) {
		return stream().filter(r -> r.getMatchRule() == matchRuleToFind).findAny()
				.orElseThrow(() -> new RuntimeException("Could not found MatchRule: " + matchRuleToFind));
	}

	public void modify(MatchRule matchRuleToFind) {
		PatternPart patternPart = get(matchRuleToFind);
		patternPart.modifyRepetition();
	}

	public boolean canModify(MatchRule matchRuleToFind) {
		PatternPart patternPart = get(matchRuleToFind);
		return patternPart.canModifyRepetition();
	}

	public List<MatchRule> toMatchRuleList() {
		List<MatchRule> list = new ArrayList<>();
		for (PatternPart patternPart : this) {
			int repetition = patternPart.getRepetition();

			MatchRule matchRule = patternPart.getMatchRule();
			for (int i = 0; i < repetition; i++) {
				list.add(matchRule);
			}
		}
		return list;
	}

	private int getKnownRepetitions() {
		int knownRepetions=0;
		for (PatternPart patternPart : this) {
			if (patternPart.hasTemporaryRepetition()) {
				knownRepetions += patternPart.getMinimumRepetition();
			} else {
				knownRepetions += patternPart.getRepetition();
			}
		}
		return knownRepetions;
	}
}
