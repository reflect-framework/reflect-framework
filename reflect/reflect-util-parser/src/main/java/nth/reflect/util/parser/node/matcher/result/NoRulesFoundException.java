package nth.reflect.util.parser.node.matcher.result;

public class NoRulesFoundException extends RuntimeException {

	private static final long serialVersionUID = -7502951489639871311L;
	public static final String MESSAGE = "Given rules where not found!";

	public NoRulesFoundException() {
		super(MESSAGE);	
	}
}
