package nth.reflect.util.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A <a href="http://en.wikipedia.org/wiki/Fluent_interface">Fluent
 * interface</a> to create regular expressions (see {@link Pattern})
 * 
 * @author nilsth
 * 
 */
public class Regex {

	private static final char[] SPECIAL_CHARS = { '\\', '[', ']', '{', '}', '*', '+', '$', '.' };
	private final StringBuilder regex;

	public Regex() {
		regex = new StringBuilder();
	}

	public java.util.regex.Pattern toPattern() {
		return java.util.regex.Pattern.compile(regex.toString());
	}

	public Matcher toMatcher(String input) {
		return toPattern().matcher(input);
	}

	public boolean hasMatchIn(String input) {
		Matcher matcher = toMatcher(input);
		return matcher.find();
	}

	public List<String> findMatches(String input) {
		Matcher matcher = toMatcher(input);
		List<String> matches = new ArrayList<>();
		while (matcher.find()) {
			String match = matcher.group();
			matches.add(match);
		}
		return matches;
	}

	public String findFirstMatchIn(String input) {
		Matcher matcher = toMatcher(input);
		if (matcher.find()) {
			String match = matcher.group();
			return match;
		}
		return null;
	}

	@Override
	public String toString() {
		return regex.toString();
	}

	public Regex beginOfLine() {
		regex.append("^");
		return this;
	}

	public Regex whiteSpace() {
		regex.append("\\s");
		return this;
	}

	public Regex whiteSpace(Repetition repetition) {
		whiteSpace();
		regex.append(repetition);
		return this;
	}

	public Regex literal(String text) {
		text = replaceSpecialChars(text);
		regex.append(text);
		return this;
	}

	public Regex literal(String text, Repetition repetition) {
		literal(text);
		regex.append(repetition);
		return this;
	}

	public Regex literals(String characters) {
		regex.append("[");
		regex.append(characters);
		regex.append("]");
		return this;
	}

	public Regex literals(String characters, Repetition repetition) {
		literals(characters);
		regex.append(repetition);
		return this;
	}

	private String replaceSpecialChars(String text) {
		for (char specialChar : SPECIAL_CHARS) {
			String specialCharWithSlash = "\\" + specialChar;
			text = text.replace(String.valueOf(specialChar), specialCharWithSlash);
		}
		return text;
	}

	public Regex anyCharacter() {
		regex.append(".");
		return this;
	}

	public Regex anyCharacter(Repetition repetition) {
		anyCharacter();
		regex.append(repetition);
		return this;
	}

	public Regex endOfLine() {
		regex.append("$");
		return this;
	}

	public Regex newLine() {
		regex.append("\\r\\n");
		return this;
	}

	public Regex multiLineMode() {
		regex.append("(?m)");
		return this;
	}

	public Regex wordChar() {
		regex.append("\\w");
		return this;
	}

	public Regex wordChar(Repetition repetition) {
		wordChar();
		regex.append(repetition);
		return this;
	}

	public Regex append(Regex partialRegex) {
		regex.append(partialRegex.toString());
		return this;
	}

	public Regex whiteSpaceWithoutCrLf() {
		regex.append("[ \\t\\x0B\\f]");
		return this;
	}

	public Regex whiteSpaceWithoutCrLf(Repetition repetition) {
		whiteSpaceWithoutCrLf();
		regex.append(repetition);
		return this;
	}

	public Regex ignoreCase() {
		regex.append("(?iu)");
		return this;
	}

	public Regex xmlStartElement(String elementName) {
		literal("<");
		whiteSpace(Repetition.zeroOrMoreTimes());
		literal(elementName);
		anyCharacter(Repetition.zeroOrMoreTimes().reluctant());
		literal(">");
		return this;
	}

	public Regex xmlEndElement(String elementName) {
		literal("</");
		whiteSpace(Repetition.zeroOrMoreTimes());
		literal(elementName);
		whiteSpace(Repetition.zeroOrMoreTimes());
		literal(">");
		return this;
	}

	public Regex or() {
		regex.append("|");
		return this;
	}

	public Regex group(Regex group) {
		regex.append("(");
		regex.append(group);
		regex.append(")");
		return this;
	}

	public Regex letters() {
		regex.append(LetterTypes.ALL_CASE);
		return this;
	}

	public Regex letters(LetterTypes letterTypes) {
		regex.append(letterTypes);
		return this;
	}

	public Regex letters(Repetition repetition) {
		letters();
		regex.append(repetition);
		return this;
	}

	public Regex letters(LetterTypes letterTypes, Repetition repetition) {
		letters(letterTypes);
		regex.append(repetition);
		return this;
	}

	public Regex group(Regex group, Repetition repetition) {
		group(group);
		regex.append(repetition);
		return this;
	}

	public Regex decimal() {
		regex.append("\\d");
		return this;
	}

	public Regex decimal(Repetition oneOrMoreTimes) {
		decimal();
		regex.append(oneOrMoreTimes);
		return this;
	}

	public Regex wordBoundary() {
		regex.append("\\b");
		return this;
	}
}
