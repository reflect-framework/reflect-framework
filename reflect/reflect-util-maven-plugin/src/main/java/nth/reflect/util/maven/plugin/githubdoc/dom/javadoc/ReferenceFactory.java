package nth.reflect.util.maven.plugin.githubdoc.dom.javadoc;

import org.jsoup.nodes.Element;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.AttributeName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementUtil;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.ReferenceName;

public abstract class ReferenceFactory {

	private static final String NONE_WORD_CHARACTER = "\\W";

	public String createHId(Element hElement) {
		switch (hElement.nodeName().toLowerCase()) {
		case ElementName.H1:
			Element h1 = hElement;
			return createH1Id(h1);
		case ElementName.H2:
			Element h2 = hElement;
			h1 = ElementUtil.findPreviousH1Element(h2);
			return createH2Id(h1, h2);
		case ElementName.H3:
			Element h3 = hElement;
			h2 = ElementUtil.findPreviousH2Element(h3);
			h1 = ElementUtil.findPreviousH1Element(h2);
			return createH3Id(h1, h2, h3);
		default:
			throw new IllegalArgumentException("Argument must be a H1,H2 or H2 element!");
		}
	}

	public String createH1Id(Element h1) {
		return createId(h1);
	}

	public String createH2Id(Element h1, Element h2) {
		StringBuilder id = new StringBuilder();
		id.append(createH1Id(h1));
		id.append("_");
		id.append(createId(h2));
		return id.toString();
	}

	public String createH3Id(Element h1, Element h2, Element h3) {
		StringBuilder id = new StringBuilder();
		id.append(createH2Id(h1, h2));
		id.append("_");
		id.append(createId(h3));
		return id.toString();
	}

	public String createId(Element element) {
		String title = element.text();
		String id = StringUtil.convertToCamelCase(title, true).replaceAll(NONE_WORD_CHARACTER, "");
		return id;
	}

	public String createHReference(Element hElement) {
		switch (hElement.nodeName().toLowerCase()) {
		case ElementName.H1:
			Element h1 = hElement;
			return createH1Reference(h1);
		case ElementName.H2:
			Element h2 = hElement;
			h1 = ElementUtil.findPreviousH1Element(h2);
			return createH2Reference(h1, h2);
		case ElementName.H3:
			Element h3 = hElement;
			h2 = ElementUtil.findPreviousH2Element(h3);
			h1 = ElementUtil.findPreviousH1Element(h2);
			return createH3Reference(h1, h2, h3);
		default:
			throw new IllegalArgumentException("Argument must be a H1,H2 or H2 element!");
		}
	}

	public abstract String createH1Reference(Element h1);

	public abstract String createH2Reference(Element h1, Element h2);

	public abstract String createH3Reference(Element h1, Element h2, Element h3);

	public String createImageSrc(Element img) {
		String src = img.attr(AttributeName.SRC);
		ReferenceName referenceName = new ReferenceName(src);
		String resourceFileName = referenceName.withoutPreFix();
		return resourceFileName;
	}

}
