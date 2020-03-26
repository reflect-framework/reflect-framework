package nth.reflect.fw.generic.plural.predicate;

import java.util.regex.Pattern;

import nth.reflect.fw.generic.plural.EnglishPlural;
import nth.reflect.fw.generic.plural.PluralRule;
import nth.reflect.fw.generic.plural.action.AppendWith;
import nth.reflect.fw.generic.plural.action.PluralAction;
import nth.reflect.fw.generic.plural.action.ReplaceIgnoreConsolantWith;
import nth.reflect.fw.generic.plural.action.ReplaceWith;

public class EndsWithPredicate {

	private static final String REGEX_END_OF_LINE = "$";
	private final Pattern pattern;
	private final String regex;

	public EndsWithPredicate(String suffixToSearchFor) {
		regex = suffixToSearchFor + REGEX_END_OF_LINE;
		pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}

	public boolean appliesTo(String singularNoun) {
		boolean matches = pattern.matcher(singularNoun).find();
		return matches;
	}

	public PluralRule replaceWith(String replacementSuffix) {
		PluralAction action = new ReplaceWith(this, replacementSuffix);
		return new PluralRule(this, action);
	}

	public PluralRule appendWith(String suffix) {
		PluralAction action = new AppendWith(suffix);
		return new PluralRule(this, action);
	}

	public PluralRule replaceIngnoreConsolantWith(String suffix) {
		PluralAction action = new ReplaceIgnoreConsolantWith(this, suffix);
		return new PluralRule(this, action);
	}

	public Pattern getPattern() {
		return pattern;
	}

	public Pattern getIgnoreConsolantPattern() {
		String regexWithoutConsolant = regex.replace(EnglishPlural.CONSOLANT, "");
		Pattern pattern = Pattern.compile(regexWithoutConsolant);
		return pattern;
	}

}
