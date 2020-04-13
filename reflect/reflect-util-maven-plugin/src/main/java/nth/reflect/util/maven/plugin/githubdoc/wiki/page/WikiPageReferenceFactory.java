package nth.reflect.util.maven.plugin.githubdoc.wiki.page;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.JSoupQuery;
import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.ReferenceFactory;

public class WikiPageReferenceFactory extends ReferenceFactory {

	private static final String MD_EXTENSION = ".md";
	private final Document javaDoc;

	public WikiPageReferenceFactory(Document javaDoc) {
		this.javaDoc = javaDoc;
	}

	public String createFileName(Element h1) {
		int chapterNumber = findChapterNumber(h1);
		StringBuilder wikiPageFile = new StringBuilder();
		wikiPageFile.append(String.format("%02d", chapterNumber));
		wikiPageFile.append("-");
		wikiPageFile.append(h1.html().replace(" ", "-"));
		wikiPageFile.append(MD_EXTENSION);
		return wikiPageFile.toString();
	}

	private int findChapterNumber(Element h1) {
		String query = new JSoupQuery().addElement(ElementName.H1).toString();
		Elements h1Elements = javaDoc.select(query);
		int chapterNumber = 0;
		for (Element h1Element : h1Elements) {
			chapterNumber++;
			if (h1Element == null || h1Element.html() == null || h1 == null || h1.html() == null) {
				System.out.println();
			}
			if (h1Element.html().equals(h1.html())) {
				return chapterNumber;
			}
		}
		throw new IllegalArgumentException("H1 element " + h1 + " could not be found!");
	}

	@Override
	public String createH1Reference(Element h1) {
		StringBuilder reference = new StringBuilder();
		reference.append(createFileName(h1).replace(MD_EXTENSION, ""));
		reference.append("#");
		reference.append(createH1Id(h1));
		return reference.toString();
	}

	@Override
	public String createH2Reference(Element h1, Element h2) {
		StringBuilder reference = new StringBuilder();
		reference.append(createFileName(h1).replace(MD_EXTENSION, ""));
		reference.append("#");
		reference.append(createH2Id(h1, h2));
		return reference.toString();
	}

	@Override
	public String createH3Reference(Element h1, Element h2, Element h3) {
		StringBuilder reference = new StringBuilder();
		reference.append(createFileName(h1).replace(MD_EXTENSION, ""));
		reference.append("#");
		reference.append(createH3Id(h1, h2, h3));
		return reference.toString();
	}

}
