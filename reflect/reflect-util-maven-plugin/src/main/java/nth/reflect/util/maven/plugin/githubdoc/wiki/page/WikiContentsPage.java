package nth.reflect.util.maven.plugin.githubdoc.wiki.page;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import nth.reflect.util.maven.plugin.githubdoc.GitHubWikiDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementUtil;

public class WikiContentsPage extends WikiPage {

	private final Element h1;
	private final WikiPageReferenceFactory referenceFactory;

	public WikiContentsPage(GitHubWikiDocumentationInfo info, Document javaDoc, Element h1) {
		super(info, javaDoc);
		this.h1 = h1;
		referenceFactory = new WikiPageReferenceFactory(javaDoc);
	}

	@Override
	public Document createContents() {
		Document doc = new Document("");

		Element divContent = doc.appendElement("div").attr("id", "content");
		List<Node> chapterNodes = ElementUtil.findChapterNodes(h1);
		ElementUtil.addAllNodes(divContent, chapterNodes);
		return doc;
	}

	@Override
	public String getTitle() {
		return h1.html();
	}

	@Override
	protected String createFileName(String title) {
		return referenceFactory.createFileName(h1);
	};

}
