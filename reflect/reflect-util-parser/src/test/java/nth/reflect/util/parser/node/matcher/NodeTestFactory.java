package nth.reflect.util.parser.node.matcher;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.token.parser.Rest;
import nth.reflect.util.parser.token.parser.Token;

public class NodeTestFactory {

	private static final Rest REST = new Rest();

	public static List<Node> createTokenNodes(String characters) {
		List<Node> tokenNodes = new ArrayList<>();
		for (Character ch : characters.toCharArray()) {
			Token token = new Token(REST, Character.toString(ch));
			TokenNode tokenNode = new TokenNode(token);
			tokenNodes.add(tokenNode);
		}
		return tokenNodes;
	}

}
