package nth.reflect.util.parser.node;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Node} is a part of a
 * <a href="https://en.wikipedia.org/wiki/Tree_structure">tree structure</a>. In
 * this case the tree represents a {@link ParseTree}
 * 
 * @author nilsth
 *
 */
public abstract class Node {

	protected static final String NEW_LINE = "\n";
	protected static final String REGEX_NEW_LINE = "\\n";
	protected static final String INDENT = "  ";
	private final List<Node> nodes;

	public Node() {
		this.nodes = new ArrayList<>();
	}

	public Node(List<Node> nodes) {
		this();
		this.nodes.addAll(nodes);
	}


	public List<Node> getNodes() {
		return nodes;
	}

	
	@Override
	public boolean equals(Object that) {
		if(this==that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (this.getClass() != that.getClass()) {
			return false;
		}
		Node thatNode=(Node) that;
		boolean equalChildren = nodes.equals(thatNode.getNodes());
		return equalChildren;
	}

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();

		reply.append(getClass().getSimpleName());
		reply.append(NEW_LINE);

		for (Node child : nodes) {
			String[] childStrings = child.toString().split(REGEX_NEW_LINE);
			for (String childString : childStrings) {
				reply.append(INDENT);
				reply.append(childString);
				reply.append(NEW_LINE);
			}
		}

		return reply.toString();
	}

}
