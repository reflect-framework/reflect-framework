package nth.reflect.util.maven.plugin.githubdoc.wiki.page;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nth.reflect.util.maven.plugin.githubdoc.GitHubWikiDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.dom.github.GitRepository;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.AttributeName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.JSoupQuery;
import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.JavaDocFactory;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.DocumentationFiles;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.ReferenceName;
import nth.reflect.util.maven.plugin.githubdoc.dom.page.FileUtil;

public class GitHubWikiPageService {

	private final GitRepository gitRepository;

	public GitHubWikiPageService() {
		this.gitRepository = new GitRepository();
	}

	public void createGitHubWikiPages(GitHubWikiDocumentationInfo info, DocumentationFiles documentationFiles,
			Document javaDoc) throws IOException {

		info.getLog().info("Writing wiki pages to: " + info.getLocalGitHubWikiRepository().getAbsolutePath());

		JavaDocFactory.updateAllReferences(javaDoc, new WikiPageReferenceFactory(javaDoc));

		List<WikiPage> webPages = createWikiPages(info, javaDoc);

		gitRepository.deleteFolderContents(info.getLocalGitHubWikiRepository());

		writeWikiPages(webPages);

		copyResources(info.getLocalGitHubWikiRepository(), documentationFiles, webPages);

		gitRepository.commitAndPush(info, info.getLocalGitHubWikiRepository());
	}

	private void copyResources(File destinationFolder, DocumentationFiles documentationFiles,
			List<WikiPage> wikiPages) {
		for (WikiPage wikiPage : wikiPages) {
			Document document = wikiPage.getContents();
			String query = new JSoupQuery().addElement(ElementName.IMG, AttributeName.SRC).toString();
			Elements imgElements = document.select(query);

			for (Element imgElement : imgElements) {
				String resourceReferenceName = imgElement.attr(AttributeName.SRC);
				ReferenceName referenceName = new ReferenceName(resourceReferenceName);
				Optional<File> result = documentationFiles.findResourceFile(referenceName);
				if (result.isPresent()) {
					File resourceFile = result.get();
					File target = new File(destinationFolder, referenceName.withoutPreFix());
					try {
						FileUtil.copyFolder(resourceFile, target);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void writeWikiPages(List<WikiPage> wikiPages) throws IOException {
		for (WikiPage wikiPage : wikiPages) {
			wikiPage.write();
		}
	}

	private List<WikiPage> createWikiPages(GitHubWikiDocumentationInfo info, Document javaDoc) {
		List<WikiPage> webPages = new ArrayList<>();
		webPages.add(new WikiHomePage(info, javaDoc.clone()));
		Elements h1Elements = javaDoc.select(ElementName.H1);
		for (Element h1 : h1Elements) {
			webPages.add(new WikiContentsPage(info, javaDoc.clone(), h1));
		}

		return webPages;
	}

}
