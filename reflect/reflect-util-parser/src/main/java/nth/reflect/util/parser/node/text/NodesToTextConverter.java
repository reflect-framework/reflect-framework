package nth.reflect.util.parser.node.text;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

public class NodesToTextConverter {
	
	private static final String SINGLE_SPACE = " ";
	private static final String REGEX_MULTIPLE_WHITESPACES = new Regex().whiteSpace(Repetition.min(2)).toString();

	public static String convert(List<Node> nodes) {
		StringBuilder result = new StringBuilder();
		for (Node node : nodes) {
			if (node instanceof TextProvider) {
				TextProvider textProvider = (TextProvider) node;
				result.append(textProvider.getText());
			}
		}
		return result.toString();
	}

	public static String convertWithoutDoubleSpaces(List<Node> nodes) {
		String text=convert(nodes);
		String textWithSingleSpaces = text.replaceAll(REGEX_MULTIPLE_WHITESPACES, SINGLE_SPACE);
		return textWithSingleSpaces;
	}
}
