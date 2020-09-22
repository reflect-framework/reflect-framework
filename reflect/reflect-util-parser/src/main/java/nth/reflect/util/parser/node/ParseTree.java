package nth.reflect.util.parser.node;

import java.util.List;

import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;

/**
 * The {@link ParseTree} is the root {@link Node} of
 * a<a href="https://en.wikipedia.org/wiki/Tree_structure">tree structure</a>.
 * <p>
 * 
 * The {@link ParseTree} is created by passing {@link Token}s (e.g. from a
 * {@link TokenParser}) into the constructor. These tokens are than converted
 * into {@link TokenNode}s that are put in its children. After this, the
 * {@link NodeParser} will combine child {@link Node}s with and replace them
 * with other {@link Node} types, that better represent the data and can contain
 * more complex logic.
 * 
 * 
 * @author nilsth
 *
 */
public class ParseTree extends Node{
	
	public ParseTree(List<Token> tokens) {
		super();
		List<Node> nodes = getNodes();
		for (Token token : tokens) {
			TokenNode tokenNode=new TokenNode(token);
			nodes.add(tokenNode);
		}
	}
}
