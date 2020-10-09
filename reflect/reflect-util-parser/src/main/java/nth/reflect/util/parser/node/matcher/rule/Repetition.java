package nth.reflect.util.parser.node.matcher.rule;

import nth.reflect.fw.generic.util.TitleBuilder;

/**
 * This is a property type used by {@link MatchRule}
 * 
 * @author nilsth
 *
 */
public class Repetition {
	private static final boolean EAGER = true;
	private final int min;
	private final int max;
	private final boolean eager;//otherwise reluctant=lazy

	public Repetition(int min, int max) {
		this(min, max, EAGER);
	}

	public Repetition(int min, int max, boolean eager) {
		super();
		this.min = min;
		this.max = max;
		this.eager = eager;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public static Repetition zeroOrMore() {
		return new Repetition(0, Integer.MAX_VALUE);
	}

	public static Repetition zeroOrOnce() {
		return new Repetition(0, 1);
	}

	public static Repetition once() {
		return new Repetition(1, 1);
	}

	public static Repetition onceOrMore() {
		return new Repetition(1, Integer.MAX_VALUE);
	}

	public boolean isEager() {
		return eager;
	}

	/**
	 * Fluent interface to set the value to reluctant<br>
	 */
	public Repetition reluctant() {
		return new Repetition(min, max, ! EAGER);
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder();
		title.append(Repetition.class.getSimpleName());
		title.append(" min=", min);
		title.append(", max=", max);
		title.append(", eager=", eager);
		return title.toString();
	}

}
