package nth.reflect.util.english.plural.action;

import java.util.regex.Pattern;

import nth.reflect.util.english.plural.predicate.EndsWithPredicate;

public class ReplaceWith implements PluralAction {

	private final Pattern pattern;
	private final String replacementSuffix;

	public ReplaceWith(EndsWithPredicate endsWithPredicate, String replacementSuffix) {
		this.replacementSuffix = replacementSuffix;
		pattern = endsWithPredicate.getPattern();
	}

	@Override
	public String makePlural(String singularNoun) {
		String result = pattern.matcher(singularNoun).replaceAll(replacementSuffix);
		return result;
	}

}
