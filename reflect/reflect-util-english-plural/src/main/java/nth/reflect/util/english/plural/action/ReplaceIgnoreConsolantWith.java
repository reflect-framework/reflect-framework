package nth.reflect.util.english.plural.action;

import java.util.regex.Pattern;

import nth.reflect.util.english.plural.predicate.EndsWithPredicate;

public class ReplaceIgnoreConsolantWith implements PluralAction {

	private final String replacementSuffix;
	private final Pattern pattern;

	public ReplaceIgnoreConsolantWith(EndsWithPredicate endsWithPredicate, String replacementSuffix) {
		this.replacementSuffix = replacementSuffix;
		this.pattern = endsWithPredicate.getIgnoreConsolantPattern();
	}

	@Override
	public String makePlural(String singularNoun) {
		String result = pattern.matcher(singularNoun).replaceAll(replacementSuffix);
		return result;
	}

}
