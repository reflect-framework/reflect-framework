package nth.reflect.util.maven.plugin.githubdoc.wiki.page;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nth.reflect.util.maven.plugin.githubdoc.GitHubWikiDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.AttributeName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.JSoupQuery;
import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.ReferenceFactory;

public class WikiHomePage extends WikiPage {

	public WikiHomePage(GitHubWikiDocumentationInfo info, Document javaDoc) {
		super(info, javaDoc);
	}

	@Override
	public Document createContents() {
		String query = new JSoupQuery().addElement(ElementName.H1).addElement(ElementName.H2).toString();
		Elements h1h2Elements = getJavaDoc().select(query);
		ReferenceFactory referenceFactory = new WikiPageReferenceFactory(getJavaDoc());
		Document doc = new Document("");
		Element ul = null;
		Element h1 = null;
		for (Element hElement : h1h2Elements) {
			if (hElement.nodeName().equals(ElementName.H1)) {
				h1 = hElement;
				Element h3 = doc.appendElement(ElementName.H3);
				Element a = h3.appendElement(ElementName.A);
				a.html(hElement.html());
				a.attr(AttributeName.HREF, referenceFactory.createH1Reference(hElement));
				ul = doc.appendElement(ElementName.UL);
			} else if (hElement.nodeName().equals(ElementName.H2)) {
				Element li = ul.appendElement(ElementName.LI);
				Element a = li.appendElement(ElementName.A);
				a.html(hElement.html());
				Element h2 = hElement;
				a.attr(AttributeName.HREF, referenceFactory.createH2Reference(h1, h2));
			}
		}
		return doc;
	}

	@Override
	protected String createFileName(String title) {
		return "Home.md";
	}

}
