package nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.tag;

import org.jsoup.nodes.Element;


public class LinkTag extends InlineTag {

	@Override
	protected String getName() {
		return "link";
	}

	@Override
	protected Element getElement(String reference) {
		if (reference.contains("#")) {
			return getReplacementForClassMember(reference);
		} else {
			return getReplacementForInternalReference(reference);
		}
	}

	private Element getReplacementForInternalReference(String name) {
		return new HtmlLinkToReference(name, name);
	}

	private Element getReplacementForClassMember(String name) {
		String text;
		String reference = "";
		if (name.startsWith("#")) {
			text = name.substring(1);
		} else {
			text = name.replace("#", ".");
			reference = name.substring(0, name.indexOf("#"));
		}
		return new HtmlLinkToReference(reference, text);
	}

}
