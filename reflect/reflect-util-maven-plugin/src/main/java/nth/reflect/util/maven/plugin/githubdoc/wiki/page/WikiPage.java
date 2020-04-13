package nth.reflect.util.maven.plugin.githubdoc.wiki.page;

import org.jsoup.nodes.Document;

import nth.reflect.util.maven.plugin.githubdoc.GitHubWikiDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.dom.page.Page;

public abstract class WikiPage extends Page {

	public WikiPage(GitHubWikiDocumentationInfo info, Document javaDoc) {
		super(info.getLocalGitHubWikiRepository(), info.getDocumentationRootClassName(), javaDoc);
	}

	// @Override
	// protected String createFileName(String title) {
	// Elements chapters=getJavaDoc().select("h1");
	// int chapterNumber=0;
	// for (Element chapter : chapters) {
	// chapterNumber++;
	// if (chapter.html().equals(title)) {
	// StringBuilder wikiPageFile = new StringBuilder();
	// wikiPageFile.append(String.format("%02d", chapterNumber));
	// wikiPageFile.append("-");
	// wikiPageFile.append(title.replace(" ", "-"));
	// wikiPageFile.append(MD_EXTENSION);
	// return wikiPageFile.toString();
	// }
	// }
	// return null;
	// }

}
