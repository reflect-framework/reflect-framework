package nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.tag;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;


public class CodeTag extends InlineTag  {


	

	private static final String CODE = "code";

	@Override
	protected String getName() {
		return CODE;
	}

	@Override
	protected Element getElement(String tagValue) {
		return new Element(Tag.valueOf(CODE),"").html(tagValue);
	}

}
