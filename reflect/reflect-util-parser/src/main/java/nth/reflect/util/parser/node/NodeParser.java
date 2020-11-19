package nth.reflect.util.parser.node;

import java.util.List;

import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.token.parser.Token;

/**
 * A {@link NodeParser} creates a {@link ParseTree} and then combines
 * {@link Node} children by replacing them with other more detailed
 * {@link Node}s as is defined by {@link NodeParserRule}s.
 * 
 * @author nilsth
 *
 */
public class NodeParser {

	private final List<NodeParserRule> nodeParserRules;

	public NodeParser(List<NodeParserRule> nodeParserRules) {
		this.nodeParserRules = nodeParserRules;
	}

	public ParseTree parse(List<Token> tokens) {
		ParseTree parseTree = new ParseTree(tokens);

		replaceAll(parseTree);

		return parseTree;
	}

	private void replaceAll(ParseTree parseTree) {
		List<Node> nodes = parseTree.getNodes();
		boolean doneReplacement = false;
		do {
			doneReplacement=false;
			for (NodeParserRule nodeParserRule : nodeParserRules) {
				doneReplacement =doneReplacement || replaceAll(nodes, nodeParserRule);
			}
		} while (doneReplacement);
	}

	private boolean replaceAll(List<Node> nodeChildren, NodeParserRule nodeParserRule) {
		boolean foundReplacement = false;
		boolean doneReplacement = false;
		do {
			foundReplacement = false;
			MatchRules matchRules = nodeParserRule.getMatchRules();
			NodeMatcher nodeMatcher=new NodeMatcher(matchRules);
			MatchResults matchResults = nodeMatcher.match(nodeChildren);
			if (matchResults.hasResults()) {
				foundReplacement = true;
				nodeParserRule.removeOrReplace(matchResults);
				doneReplacement = true;
			}
		} while (foundReplacement);

		doneReplacement = doneReplacement || replaceAllInChildrenRecursively(nodeChildren, nodeParserRule);
		return doneReplacement;
	}

	private boolean replaceAllInChildrenRecursively(List<Node> nodeChildren, NodeParserRule nodeReplacement) {
		boolean doneReplacement = false;
		for (Node node : nodeChildren) {
			doneReplacement = replaceAll(node.getNodes(), nodeReplacement);
		}
		return doneReplacement;
	}
}
