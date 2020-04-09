package nth.reflect.util.english.plural;

import java.util.ArrayList;

import nth.reflect.util.english.plural.action.AppendWith;

/**
 * {@link EnglishPlural} converts a English singular noun to a plural form by a
 * set of rules. Note this is on best effort. Not all forms are covered! <br>
 * Based on <a href=
 * "https://www.grammarly.com/blog/plural-nouns/">https://www.grammarly.com/blog/plural-nouns/</a>
 * 
 * @author nilsth
 *
 */
public class EnglishPlural {

	private static ArrayList<PluralRule> rules;

	public static String CONSOLANT = "[b-df-hj-np-tv-z]";

	static {
		rules = new ArrayList();

		rules.add(PluralRule.endsWith("sheep").replaceWith("sheep"));
		rules.add(PluralRule.endsWith("series").replaceWith("series"));
		rules.add(PluralRule.endsWith("species").replaceWith("species"));
		rules.add(PluralRule.endsWith("deer").replaceWith("deer"));
		rules.add(PluralRule.endsWith("fish").replaceWith("fish"));
		// Some nouns don’t change at all when they’re pluralized.
		// sheep – sheep
		// series – series
		// species – species
		// deer – deer
		// fish - fish

		rules.add(PluralRule.endsWith("child").replaceWith("children"));
		rules.add(PluralRule.endsWith("goose").replaceWith("geese"));
		rules.add(PluralRule.endsWith("man").replaceWith("men"));
		rules.add(PluralRule.endsWith("woman").replaceWith("women"));
		rules.add(PluralRule.endsWith("tooth").replaceWith("teeth"));
		rules.add(PluralRule.endsWith("foot").replaceWith("feet"));
		rules.add(PluralRule.endsWith("mouse").replaceWith("mice"));
		rules.add(PluralRule.endsWith("person").replaceWith("people"));
		// Plural Noun Rules for Irregular Nouns
		// child – children
		// goose – geese
		// man – men
		// woman – women
		// tooth – teeth
		// foot – feet
		// mouse – mice
		// person – people

		rules.add(PluralRule.endsWith("fez").replaceWith("fezzes"));
		rules.add(PluralRule.endsWith("gas").replaceWith("gasses"));
		// exceptions on the following rule
		// fez – fezzes
		// gas –gasses

		rules.add(PluralRule.endsWith("roof").replaceWith("roofs"));
		rules.add(PluralRule.endsWith("belief").replaceWith("beliefs"));
		rules.add(PluralRule.endsWith("chef").replaceWith("chefs"));
		rules.add(PluralRule.endsWith("chief").replaceWith("chiefs"));
		// exceptions on the following rule
		// roof – roofs
		// belief – beliefs
		// chef – chefs
		// chief – chiefs

		rules.add(PluralRule.endsWith("f").replaceWith("ves"));
		// e.g.
		// wolf – wolves

		rules.add(PluralRule.endsWith("fe").replaceWith("ves"));
		// e.g.
		// wife – wives

		rules.add(PluralRule.endsWith(CONSOLANT + "y").replaceIngnoreConsolantWith("ies"));
		// e.g.
		// city – cities
		// puppy – puppies

		rules.add(PluralRule.endsWith("photo").replaceWith("photos"));
		rules.add(PluralRule.endsWith("piano").replaceWith("pianos"));
		rules.add(PluralRule.endsWith("halo").replaceWith("halos"));
		// Exceptions on following rule
		// photo – photos
		// piano – pianos
		// halo – halos

		rules.add(PluralRule.endsWith(CONSOLANT + "o").replaceIngnoreConsolantWith("oes"));
		// e.g.
		// potato – potatoes
		// tomato – tomatoes

		rules.add(PluralRule.endsWith("is").replaceWith("es"));
		// e.g.
		// analysis – analyses
		// ellipsis – ellipses

		rules.add(PluralRule.endsWith("on").replaceWith("a"));
		// e.g.
		// phenomenon – phenomena
		// criterion – criteria

		rules.add(PluralRule.endsWith("s").appendWith("es"));
		rules.add(PluralRule.endsWith("ss").appendWith("es"));
		rules.add(PluralRule.endsWith("sh").appendWith("es"));
		rules.add(PluralRule.endsWith("ch").appendWith("es"));
		rules.add(PluralRule.endsWith("x").appendWith("es"));
		rules.add(PluralRule.endsWith("z").appendWith("es"));
		// e.g.
		// truss – trusses
		// bus – buses
		// marsh – marshes
		// lunch – lunches
		// tax – taxes
		// blitz – blitzes

	}

	public static String fromSingularNoun(String singularNoun) {
		for (PluralRule rule : rules) {
			if (rule.appliesTo(singularNoun)) {
				String result = rule.makePlural(singularNoun);
				return result;
			}
		}
		return new AppendWith("s").makePlural(singularNoun);
		// default: e.g.
		// cat – cats
		// house – houses
	}

}
