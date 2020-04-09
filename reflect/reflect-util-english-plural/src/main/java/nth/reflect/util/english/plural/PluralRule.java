package nth.reflect.util.english.plural;

import nth.reflect.util.english.plural.action.PluralAction;
import nth.reflect.util.english.plural.predicate.EndsWithPredicate;

public class PluralRule {

	private EndsWithPredicate predicate;
	private PluralAction action;

	public PluralRule(EndsWithPredicate predicate, PluralAction action) {
		this.predicate = predicate;
		this.action = action;
	}

	public boolean appliesTo(String singularNoun) {
		return predicate.appliesTo(singularNoun);
	}

	public String makePlural(String singularNoun) {
		return action.makePlural(singularNoun);
	}

	public static EndsWithPredicate endsWith(String suffixToSearchFor) {
		return new EndsWithPredicate(suffixToSearchFor);
	}

}
