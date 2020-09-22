package nth.reflect.util.parser.node.matcher.rule;

import nth.reflect.fw.generic.util.TitleBuilder;

/**
 * This is a property type used by {@link MatchRule}
 * 
 * @author nilsth
 *
 */
public class Repetition {
	private final int min;
	final int max;

	public Repetition(int min, int max) {
		super();
		this.min = min;
		this.max = max;
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

	public static Repetition oneTime() {
		return new Repetition(1, 1);
	}

	public static Repetition oneOrMore() {
		return new Repetition(1, Integer.MAX_VALUE);
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder();
		title.append(Repetition.class.getSimpleName());
		title.append(" min=", min);
		title.append(", max=", max);
		return title.toString();
	}

}
