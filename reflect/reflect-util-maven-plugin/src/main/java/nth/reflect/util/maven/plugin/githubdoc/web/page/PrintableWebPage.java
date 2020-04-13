package nth.reflect.util.maven.plugin.githubdoc.web.page;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.util.maven.plugin.githubdoc.GitHubWebDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.AttributeName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementUtil;

public class PrintableWebPage extends WebPage {

	public PrintableWebPage(GitHubWebDocumentationInfo info, Document javaDoc) {
		super(info.getLocalGitHubWebRepository(), info.getDocumentationRootClassName(), javaDoc);
	}

	@Override
	public Document createContents() {
		Document doc = createDocument(getTitle());

		createHead(doc);

		Element body = doc.body();

		createFrontCover(getJavaDoc(), body);

		createTableOfContents(getJavaDoc(), body);

		createContent(getJavaDoc(), body);

		return doc;

	}

	@Override
	protected String createFileName(String title) {
		StringBuilder fileName = new StringBuilder();
		fileName.append(StringUtil.convertToCamelCase(title, true));
		fileName.append("_Printable.html");
		return fileName.toString();
	}

	private static void createHead(Document doc) {
		Element head = doc.head();
		head.appendElement("meta").attr("charset", "utf-8");
		head
				.appendElement("meta")
				.attr("name", "viewport")
				.attr("content", "width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes");
	}

	private static Document createDocument(String title) {
		Document doc = new Document("");
		doc.appendChild(new DocumentType(ElementName.HTML, "", "", ""));
		Element html = doc.appendElement(ElementName.HTML);
		html.appendElement(ElementName.HEAD);
		html.appendElement(ElementName.BODY);
		doc.title(title);
		return doc;
	}

	private static void createContent(Document javaDoc, Element body) {
		Element divContent = body.appendElement(ElementName.DIV).attr(AttributeName.ID, "content");
		Elements h1Elements = javaDoc.select(ElementName.H1);
		for (Element h1 : h1Elements) {
			List<Node> chapterNodes = ElementUtil.findChapterNodes(h1);
			ElementUtil.addAllNodes(divContent, chapterNodes);
		}

	}

	private static void createFrontCover(Document javaDoc, Element body) {
		Element firstChapter = javaDoc.select(ElementName.H1).first();
		List<Node> frontCoverElements = ElementUtil.findAllNodesBefore(firstChapter);
		Element divFrontCover = body.appendElement(ElementName.DIV).attr(AttributeName.ID, "frontCover");
		ElementUtil.addAllNodes(divFrontCover, frontCoverElements);
	}

	private static void createTableOfContents(Document javaDoc, Element body) {
		Element divToc = body.appendElement(ElementName.DIV).attr(AttributeName.ID, "tableOfContents");
		divToc.appendElement(ElementName.H1).html("Contents");
		Element ulh2 = null;
		Elements chaptersAndParagraph = javaDoc.select("h1,h2");
		for (Element chapterOrParagraph : chaptersAndParagraph) {
			if (ElementName.H1.equals(chapterOrParagraph.tagName())) {
				divToc
						.appendElement(ElementName.H3)
						.appendElement(ElementName.A)
						.attr(AttributeName.HREF, "#" + chapterOrParagraph.id())
						.html(chapterOrParagraph.html());
				ulh2 = divToc.appendElement(ElementName.UL);
			}
			if (ElementName.H2.equals(chapterOrParagraph.tagName())) {
				ulh2
						.appendElement(ElementName.LI)
						.appendElement(ElementName.A)
						.attr(AttributeName.HREF, "#" + chapterOrParagraph.id())
						.html(chapterOrParagraph.html());
			}
		}
	}

}
