package nth.introspect.generic.regex;

public class Repetition {

	private static final String MODE_GREEDY = "";
	private static final String MODE_RELUCTANT = "?";
	private static final String MODE_POSSESSIVE = "+";
	private String regex;
	private String mode=MODE_GREEDY;

	private Repetition(String regex) {
		this.regex = regex;
	}

	public static Repetition zeroOrMoreTimes() {
		return new Repetition("*");
	}

	public static Repetition oneOrMoreTimes() {
		return new Repetition("+");
	}

	public Repetition reluctant() {
		mode=MODE_RELUCTANT;
		return this;
	}
	
	public Repetition possessive() {
		mode=MODE_POSSESSIVE;
		return this;
	}
	
	@Override
	public String toString() {
		return regex+mode;
	}
	
	

}
