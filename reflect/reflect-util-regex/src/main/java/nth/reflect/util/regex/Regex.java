package nth.reflect.util.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

	public Regex(String regexString) {
		regex = new StringBuilder(regexString);
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

	public Regex append(Regex partialRegex) {
		regex.append(partialRegex.toString());
		return this;
	}

	public Regex append(Regex partialRegex, Repetition repetition) {
		regex.append(partialRegex.toString());
		regex.append(repetition);
		return this;
	}

	public Regex beginOfLine() {
		regex.append("^");
		return this;
	}

	public Regex digit() {
		regex.append("\\d");
		return this;
	}

	public Regex digit(Repetition oneOrMoreTimes) {
		digit();
		regex.append(oneOrMoreTimes);
		return this;
	}

	public Regex endOfLine() {
		regex.append("$");
		return this;
	}

	public Optional<String> findFirstMatchIn(String input) {
		Matcher matcher = toMatcher(input);
		if (matcher.find()) {
			String match = matcher.group();
			return Optional.of(match);
		}
		return Optional.empty();
	}

	public List<String> findMatches(String input) {
		Matcher matcher = toMatcher(input);
		return matcher.results().map(MatchResult::group).collect(Collectors.toList());
	}


	public List<String> findGroups(String input) {
		List<String> groups=new ArrayList<>();
		Matcher matcher = toMatcher(input);
		List<MatchResult> results = matcher.results().collect(Collectors.toList());
		if (results.size()==1) {
			MatchResult result = results.get(0);
			for (int i=0;i<=result.groupCount();i++) {
				String group = result.group(i);
				groups.add(group);
			}
		}
		return groups;
	}
	
	public Regex group(Regex group) {
		regex.append("(");
		regex.append(group);
		regex.append(")");
		return this;
	}

	public Regex group(Regex group, Repetition repetition) {
		group(group);
		regex.append(repetition);
		return this;
	}

	public boolean hasMatchIn(String input) {
		Matcher matcher = toMatcher(input);
		return matcher.find();
	}

	public Regex ignoreCase() {
		regex.append("(?iu)");
		return this;
	}

	public Regex letter() {
		regex.append(LetterTypes.ALL_CASE);
		return this;
	}

	public Regex letter(LetterTypes letterTypes) {
		regex.append(letterTypes);
		return this;
	}

	public Regex letter(LetterTypes letterTypes, Repetition repetition) {
		letter(letterTypes);
		regex.append(repetition);
		return this;
	}

	public Regex letter(Repetition repetition) {
		letter();
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

	public Regex multiLineMode() {
		regex.append("(?m)");
		return this;
	}

	public Regex newLine() {
		regex.append("\\r\\n");
		return this;
	}

	public Regex noneCapturingGroup(Regex group) {
		regex.append("(?:");
		regex.append(group);
		regex.append(")");
		return this;
	}

	
	public Regex noneCapturingGroup(Regex group, Repetition repetition) {
		regex.append("(?:");
		regex.append(group);
		regex.append(")");
		regex.append(repetition);
		return this;
	}

	public Regex or(List<Regex> regExpressions) {
		StringBuilder group = new StringBuilder();
		boolean appendOr=false;
		for (Regex orRegex : regExpressions) {
			if (appendOr) {
				group.append("|");				
			} else {
				appendOr=true;
			}
			group.append(orRegex);
		}
		Regex regex=new Regex(group.toString());
		noneCapturingGroup(regex);
		return this;
	}

	public Regex or(List<Regex> regExpressions, Repetition repetition) {
		or(regExpressions);
		regex.append(repetition);
		return this;
	}

	public String removeAllFrom(String input) {
		String result = replaceAll(input, "");
		return result;
	}

	public String replaceAll(String input, String replacement) {
		Matcher matcher = toMatcher(input);
		String result = matcher.replaceAll(replacement);
		return result;
	}

	private String replaceSpecialChars(String text) {
		for (char specialChar : SPECIAL_CHARS) {
			String specialCharWithSlash = "\\" + specialChar;
			text = text.replace(String.valueOf(specialChar), specialCharWithSlash);
		}
		return text;
	}
	
	public Matcher toMatcher(String input) {
		return toPattern().matcher(input);
	}


	public java.util.regex.Pattern toPattern() {
		return java.util.regex.Pattern.compile(regex.toString());
	}
	
	@Override
	public String toString() {
		return regex.toString();
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
	
	public Regex whiteSpaceWithoutCrLf() {
		regex.append("[ \\t\\x0B\\f]");
		return this;
	}

	public Regex whiteSpaceWithoutCrLf(Repetition repetition) {
		whiteSpaceWithoutCrLf();
		regex.append(repetition);
		return this;
	}

	public Regex wordBoundary() {
		regex.append("\\b");
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

	public Regex xmlEndElement(String elementName) {
		literal("</");
		whiteSpace(Repetition.zeroOrMoreTimes());
		literal(elementName);
		whiteSpace(Repetition.zeroOrMoreTimes());
		literal(">");
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



}
