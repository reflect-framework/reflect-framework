package nth.reflect.util.parser.token.parser;

/**
 * A lexical {@link Token} or simply {@link Token} is a string with an assigned
 * and thus identified meaning. It is structured as a pair consisting of a
 * {@link TokenRule} and an optional token value.
 * 
 * @author nilsth
 *
 */
public class Token {
	private final TokenRule rule;
	private final String value;

	public Token(TokenRule rule, String value) {
		super();
		this.rule = rule;
		this.value = value;

	}

	public TokenRule getRule() {
		return rule;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object that) {
		if (that == null) {
			return false;
		}
		if (this.getClass() != that.getClass()) {
			return false;
		}
		Token thatToken = (Token) that;
		if (!this.getValue().equals(thatToken.getValue())) {
			return false;
		}
		if (this.getRule().getClass() != thatToken.getRule().getClass()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return toShortString();
	}

	public String toShortString() {
		if (rule.hasValue()) {
			return "[" + value + "]";
		} else {
			return "{" + rule.getClass().getSimpleName() + "}";
		}
	}
	
	public String toLongString() {
		StringBuilder reply=new StringBuilder();
		reply.append(Token.class.getSimpleName());
		reply.append(".");
		reply.append(rule.getClass().getSimpleName());
		if (rule.hasValue()) {
			reply.append(" = ");
			reply.append(value);
		} 
		return reply.toString();
	}

}
