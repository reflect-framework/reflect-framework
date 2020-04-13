package nth.reflect.util.maven.plugin.githubdoc.web.page;

import java.io.File;

import org.jsoup.nodes.Document;

import nth.reflect.util.maven.plugin.githubdoc.GitHubWebDocumentationInfo;

/**
 * Index files that redirects the user to the MaterialAppBarTitle page
 * 
 * @author nilsth
 *
 */
public class IndexWebPage extends WebPage {

	private static final String INDEX_HTML = "index.html";
	private final File homePage;

	public IndexWebPage(GitHubWebDocumentationInfo info, Document javaDoc, File homePage) {
		super(info.getLocalGitHubWebRepository(), info.getDocumentationRootClassName(), javaDoc);
		this.homePage = homePage;
	}

	@Override
	public Document createContents() {
		Document doc = Document.createShell("");
		StringBuilder content = new StringBuilder();
		content.append("0; url=");
		content.append(homePage.getName());
		doc.head().appendElement("meta").attr("http-equiv", "refresh").attr("content", content.toString());
		return doc;
	}

	@Override
	protected File createFile(String title) {
		StringBuilder filePath = new StringBuilder();
		filePath.append(getDestinationFolder());
		filePath.append("/");
		filePath.append(INDEX_HTML);
		File file = new File(filePath.toString());
		return file;
	}

}
