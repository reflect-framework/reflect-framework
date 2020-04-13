package nth.reflect.util.maven.plugin.githubdoc.web.page;

import java.util.List;

import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.util.maven.plugin.githubdoc.GitHubWebDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementUtil;

public class FancyWebPage extends WebPage {

	public FancyWebPage(GitHubWebDocumentationInfo info, Document javaDoc) {
		super(info.getLocalGitHubWebRepository(), info.getDocumentationRootClassName(), javaDoc);
	}

	@Override
	public Document createContents() {
		String title = getTitle();

		Document doc = createDocument(title);

		createHead(doc);

		Element body = doc.body();
		Element divPage = body.appendElement("div").attr("id", "page");

		createTitleBar(title, divPage);

		createContents(getJavaDoc(), divPage);

		createMenu(getJavaDoc(), divPage);

		return doc;
	}

	private Document createDocument(String title) {
		Document doc = new Document("");
		doc.appendChild(new DocumentType("html", "", "", ""));
		Element html = doc.appendElement("html");
		html.appendElement("head");
		html.appendElement("body");
		doc.title(title);
		return doc;
	}

	private void createTitleBar(String title, Element divPage) {
		Element divHeader = divPage.appendElement("div").attr("class", "header Fixed");
		divHeader.appendElement("a").attr("href", "#menu");
		divHeader.append(title);
	}

	private void createContents(Document javaDoc, Element divPage) {
		Element divContent = divPage.appendElement("div").attr("class", "content").attr("id", "content");
		Elements h1Elements = javaDoc.select("h1");
		for (Element h1 : h1Elements) {
			List<Node> chapterNodes = ElementUtil.findChapterNodes(h1);
			ElementUtil.addAllNodes(divContent, chapterNodes);
		}
	}

	private void createHead(Document doc) {
		Element head = doc.head();
		head.appendElement("meta").attr("charset", "utf-8");
		head
				.appendElement("meta")
				.attr("name", "viewport")
				.attr("content", "width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes");

		head.appendElement("link").attr("type", "text/css").attr("rel", "stylesheet").attr("href", "css/demo.css");
		head
				.appendElement("link")
				.attr("type", "text/css")
				.attr("rel", "stylesheet")
				.attr("href", "dist/core/css/jquery.mmenu.all.css");
		head
				.appendElement("link")
				.attr("type", "text/css")
				.attr("rel", "stylesheet")
				.attr("href", "dist/addons/css/jquery.mmenu.dragopen.css");

		head
				.appendElement("script")
				.attr("type", "text/javascript")
				.attr("src", "http://hammerjs.github.io/dist/hammer.min.js");
		head
				.appendElement("script")
				.attr("type", "text/javascript")
				.attr("src", "http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js");
		head.appendElement("script").attr("type", "text/javascript").attr("src", "dist/core/js/jquery.mmenu.min.js");
		head
				.appendElement("script")
				.attr("type", "text/javascript")
				.attr("src", "dist/addons/js/jquery.mmenu.dragopen.min.js");
		head
				.appendElement("script")
				.attr("type", "text/javascript")
				.attr("src", "dist/addons/js/jquery.mmenu.fixedelements.min.js");
		head
				.appendElement("script")
				.attr("type", "text/javascript")
				.appendChild(new DataNode(getJavaScriptToEnsureTitleVisibilityForBookmarks(), ""));
		head
				.appendElement("script")
				.attr("type", "text/javascript")
				.appendChild(new DataNode(getJavaScriptToHandleMenu(), ""));
	}

	private void createMenu(Document javaDoc, Element divPage) {
		Element nav = divPage.appendElement("nav").attr("id", "menu");
		Element ulh1 = nav.appendElement("ul");
		Element ulh2 = null;
		Elements chaptersAndParagraph = javaDoc.select("h1,h2");
		for (Element chapterOrParagraph : chaptersAndParagraph) {
			if ("h1".equals(chapterOrParagraph.tagName())) {
				Element lih1 = ulh1
						.appendElement("li")
						.appendElement("a")
						.attr("href", "#" + chapterOrParagraph.id())
						.html(chapterOrParagraph.html());
				ulh2 = lih1.appendElement("ul");
			}
			if ("h2".equals(chapterOrParagraph.tagName())) {
				ulh2
						.appendElement("li")
						.appendElement("a")
						.attr("href", "#" + chapterOrParagraph.id())
						.html(chapterOrParagraph.html());
			}
		}
	}

	private String getJavaScriptToEnsureTitleVisibilityForBookmarks() {
		StringBuilder js = new StringBuilder();
		// see: https://github.com/twbs/bootstrap/issues/1768
		js.append("var shiftWindow = function() { scrollBy(0, -50) };");
		js.append("if (location.hash) shiftWindow();");
		js.append("window.addEventListener('hashchange', shiftWindow);");
		return js.toString();
	}

	private String getJavaScriptToHandleMenu() {
		StringBuilder js = new StringBuilder();

		js.append("$(function() {");
		js.append("	var $menu = $('nav#menu'),");
		js.append("		$html = $('html, body');");
		js.append("");
		js.append("	$menu.mmenu({");
		js.append("		dragOpen: true");
		js.append("	});");
		js.append("");
		js.append("	var $anchor = false;");
		js.append("	$menu.find( 'li > a' ).on(");
		js.append("		'click',");
		js.append("		function( e )");
		js.append("		{");
		js.append("			$anchor = $(this);");
		js.append("		}");
		js.append("	);");
		js.append("");
		js.append("	var api = $menu.data( 'mmenu' );");
		js.append("	api.bind( 'closed',");
		js.append("		function()");
		js.append("		{");
		js.append("			if ( $anchor )");
		js.append("			{");
		js.append("				var href = $anchor.attr( 'href' );");
		js.append("				$anchor = false;");
		js.append("");
		js.append("				if ( href.slice( 0, 1 ) == '#' )");
		js.append("				{");
		js.append("					$html.animate({");
		js.append("						scrollTop: $( href ).offset().top -50");
		js.append("					});	");
		js.append("				}");
		js.append("			}");
		js.append("		}");
		js.append("	);");
		js.append("});");

		return js.toString();
	}

	@Override
	protected String createFileName(String title) {
		StringBuilder fileName = new StringBuilder();
		fileName.append(StringUtil.convertToCamelCase(title, true));
		fileName.append(".html");
		return fileName.toString();
	}

}
