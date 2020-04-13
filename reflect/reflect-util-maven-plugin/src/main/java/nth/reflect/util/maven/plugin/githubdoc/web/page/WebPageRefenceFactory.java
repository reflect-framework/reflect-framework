package nth.reflect.util.maven.plugin.githubdoc.web.page;

import org.jsoup.nodes.Element;

import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.ReferenceFactory;

public class WebPageRefenceFactory extends ReferenceFactory {

	@Override
	public String createH1Reference(Element h1) {
		StringBuilder reference = new StringBuilder();
		reference.append("#");
		reference.append(createH1Id(h1));
		return reference.toString();
	}

	@Override
	public String createH2Reference(Element h1, Element h2) {
		StringBuilder reference = new StringBuilder();
		reference.append("#");
		reference.append(createH2Id(h1, h2));
		return reference.toString();
	}

	@Override
	public String createH3Reference(Element h1, Element h2, Element h3) {
		StringBuilder reference = new StringBuilder();
		reference.append("#");
		reference.append(createH3Id(h1, h2, h3));
		return reference.toString();
	}

}
