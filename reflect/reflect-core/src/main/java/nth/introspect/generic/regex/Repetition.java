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

	public static Repetition onceOrNotAtAll() {
		return new Repetition("?");
	}
	
	public static Repetition zeroOrMoreTimes() {
		return new Repetition("*");
	}

	public static Repetition oneOrMoreTimes() {
		return new Repetition("+");
	}

	public static Repetition times(int times) {
		StringBuilder expression=new StringBuilder();
		expression.append("{");
		expression.append(times);
		expression.append("}");
		return new Repetition(expression.toString());
	}
	
	public static Repetition minMax(int min, int max) {
		StringBuilder expression=new StringBuilder();
		expression.append("{");
		expression.append(min);
		expression.append(",");
		expression.append(max);
		expression.append("}");
		return new Repetition(expression.toString());
	}

	public static Repetition min(int min) {
		StringBuilder expression=new StringBuilder();
		expression.append("{");
		expression.append(min);
		expression.append(",}");
		return new Repetition(expression.toString());
	}

	public static Repetition max(int max) {
		StringBuilder expression=new StringBuilder();
		expression.append("{,");
		expression.append(max);
		expression.append("}");
		return new Repetition(expression.toString());
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
