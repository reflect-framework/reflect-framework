package nth.reflect.util.parser.node;

import nth.reflect.util.parser.node.text.TextProvider;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenRule;

/**
 * A wrapper of a {@link Token} so that it can be put in a {@link ParseTree}. It
 * most likely will be replaced by the {@link NodeParser} by a more complex
 * {@link Node} type
 * 
 * @author nilsth
 *
 */
public class TokenNode extends Node implements TextProvider {

	private final Token token;

	public TokenNode(Token token) {
		super();
		this.token = token;
	}

	public TokenNode(TokenRule tokenRule, String value) {
		this(new Token(tokenRule, value));
	}

	public TokenRule getRule() {
		return token.getRule();
	}

	public Token getToken() {
		return token;
	}

	@Override
	public boolean equals(Object that) {
		if (!super.equals(that)) {
			return false;
		}
		if (that instanceof TokenNode) {
			TokenNode thatTokenNode = (TokenNode) that;
			boolean equalTokens = token.equals(thatTokenNode.getToken());
			return equalTokens;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();

		reply.append(getClass().getSimpleName());
		reply.append(" type="+token.getRule().getClass().getSimpleName());
		if (token.getRule().hasValue()) {
			reply.append(" value="+token.getValue());			
		}
		return reply.toString();
	}

	public String getValue() {
		return token.getValue();
	}

	@Override
	public String getText() {
		return token.getValue();
	}

}
