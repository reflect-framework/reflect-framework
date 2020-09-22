package nth.reflect.util.parser.token.parser;

import nth.reflect.util.regex.Regex;
/**
 * Defines a {@link Token} with {@link Regex}
 * @author nilsth
 *
 */
public interface TokenRule {
	public Regex getRegex();

	public boolean hasValue(); 
}
