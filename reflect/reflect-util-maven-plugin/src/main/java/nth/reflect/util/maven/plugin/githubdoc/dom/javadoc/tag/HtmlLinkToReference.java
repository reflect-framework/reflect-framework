package nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.tag;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.ReferenceName;

public class HtmlLinkToReference extends Element {

	public static final String HREF = "href";
	public static final String ID = "id";

	public HtmlLinkToReference(String reference, String text) {
		super(Tag.valueOf("a"),"" );
		html(text);
		if (reference!=null) {
			attr(HREF, ReferenceName.PREFIX+reference);
		}
	}

}
