package nth.reflect.fw.generic.plural;

import nth.reflect.fw.generic.plural.action.PluralAction;
import nth.reflect.fw.generic.plural.predicate.EndsWithPredicate;

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
