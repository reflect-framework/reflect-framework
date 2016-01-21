package nth.introspect.generic.regex;

public enum LetterTypes {
	UPPER_CASE("[A-Z]"), LOWER_CASE("[a-z]"), ALL_CASE("[a-zA-Z]");
	private final String regex;

	private LetterTypes(String regex) {
		this.regex = regex;
	}

	public String toString() {
		return regex;
	};
}
