package nth.reflect.util.maven.plugin.githubdoc.dom.javafile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nth.reflect.util.regex.Regex;

public class RegexParser {

	private static final int NOT_FOUND = -1;
	private String result;

	public RegexParser(String text) {
		this.result = new String(text);
	}

	public List<String> findAll(Regex regEx) {
		List<String> results = new ArrayList<>();
		Matcher matcher = Pattern.compile(regEx.toString()).matcher(result);
		while (matcher.find()) {
			String result = matcher.group();
			results.add(result);
		}
		return results;
	}

	public void removeAll(Pattern pattern) {
		result = pattern.matcher(result).replaceAll("");
	}

	public String getResult() {
		return result;
	}

	public boolean startsWith(String prefix) {
		return result.startsWith(prefix);
	}

	public void removeFirst(String textToRemove) {
		int startPos = result.indexOf(textToRemove);
		if (startPos != NOT_FOUND) {
			startPos += textToRemove.length();
			result = result.substring(startPos);
		}
	}

	public void removeFrom(Regex regex) {
		Matcher matcher = regex.toMatcher(result);
		if (matcher.find()) {
			int startPos =matcher.start();
			result = result.substring(0, startPos);
		}
	}

	public void replaceAll(Regex regex, String replacement) {
		result = result.replaceAll(regex.toString(), replacement);
	}

	@Override
	public String toString() {
		return result;
	}

	public boolean startsWith(Pattern startPattern) {
		return startPattern.matcher(result).lookingAt();
	}

	public void removeAllBehind(Pattern pattern) {
		Matcher matcher = pattern.matcher(result);
		if (matcher.find()) {
			int endIndex = matcher.start();
			result = result.substring(0, endIndex);
		}
	}

	public void removeAllBeforeAndIncluding(Pattern pattern) {
		Matcher matcher = pattern.matcher(result);
		if (matcher.find()) {
			int startIndex = matcher.end();
			result = result.substring(startIndex);
		}
	}

	public String findFirst(Pattern pattern) {
		Matcher matcher = pattern.matcher(result);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	public void removeFirst(Pattern pattern) {
		result=pattern.matcher(result).replaceFirst("");
	}

	public void trim() {
		result=result.trim();
	}

	public void insert(String textToInsert,  int offset) {
		StringBuilder builder=new StringBuilder(result);
		builder.insert(offset, textToInsert);
		result=builder.toString();
	}

	public void append(String textToAppend) {
		StringBuilder builder=new StringBuilder(result);
		builder.append(textToAppend);
		result=builder.toString();
	}

}
