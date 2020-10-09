package nth.reflect.util.parser.node.matcher;

import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.token.parser.Rest;

public class PredicateTestFactory {

	public static Predicate<Node> create(String textToFind) {
		return new Predicate<Node>() {

			@Override
			public boolean test(Node node) {
				if (node instanceof TokenNode) {
					TokenNode tokenNode = (TokenNode) node;
					if (!tokenNode.getRule().getClass().equals(Rest.class)) {
						return false;
					}
					return tokenNode.getValue().equals(textToFind);
				} else {
					return false;
				}
			}
			
			@Override
			public String toString() {
				return textToFind;
			}
		};
	}

	public static Predicate<Node> createAny() {
		return new Predicate<Node>() {

			@Override
			public boolean test(Node t) {
				return true;
			}
			
			@Override
			public String toString() {
				return "*";
			}
		};
	}

}
