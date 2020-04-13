package nth.reflect.util.maven.plugin.githubdoc.web.page;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nth.reflect.util.maven.plugin.githubdoc.GitHubWebDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.dom.github.GitRepository;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.AttributeName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.JSoupQuery;
import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.JavaDocFactory;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.DocumentationFiles;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.ReferenceName;
import nth.reflect.util.maven.plugin.githubdoc.dom.page.FileUtil;
import nth.reflect.util.maven.plugin.githubdoc.dom.page.WritableFile;

public class GitHubWebPageService {

	private final GitRepository gitRepository;

	public GitHubWebPageService() {
		this.gitRepository = new GitRepository();
	}

	public void createGitHubWebPages(GitHubWebDocumentationInfo info, DocumentationFiles documentationFiles,
			Document javaDoc) throws IOException {

		info.getLog().info("Writing web pages to: " + info.getLocalGitHubWebRepository().getAbsolutePath());

		JavaDocFactory.updateAllReferences(javaDoc, new WebPageRefenceFactory());

		List<WritableFile> webPages = createWebPages(info, javaDoc);

		gitRepository.deleteFolderContents(info.getLocalGitHubWebRepository());

		writeWebPages(webPages);

		copyResources(info.getLocalGitHubWebRepository(), documentationFiles, webPages);

		copyMenuFiles(info.getLocalGitHubWebRepository());

		gitRepository.commitAndPush(info, info.getLocalGitHubWebRepository());
	}

	private void copyMenuFiles(File destinationFolder) {
		try {
			URL packagePath = getClass().getResource("/mmenu");

			FilesFromJar.copy(packagePath, destinationFolder);

		} catch (Exception e) {
			throw new RuntimeException("Error copying the javascript mmenu files.", e);
		}

	}

	private void copyResources(File destinationFolder, DocumentationFiles documentationFiles,
			List<WritableFile> webPages) {
		for (WritableFile page : webPages) {

			if (page instanceof WebPage) {
				WebPage webPage = (WebPage) page;
				Document document = webPage.getContents();
				String query = new JSoupQuery().addElement(ElementName.IMG, AttributeName.SRC).toString();
				Elements imgElements = document.select(query);

				for (Element imgElement : imgElements) {
					String elementName = imgElement.nodeName();
					if (elementName.equals(ElementName.IMG)) {
						String src = imgElement.attr(AttributeName.SRC);
						ReferenceName referenceName = new ReferenceName(src);
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
		}
	}

	private void writeWebPages(List<WritableFile> webPages) throws IOException {
		for (WritableFile webPage : webPages) {
			webPage.write();
		}
	}

	private List<WritableFile> createWebPages(GitHubWebDocumentationInfo info, Document javaDoc) {

		FancyWebPage fancyWebPage = new FancyWebPage(info, javaDoc.clone());

		List<WritableFile> webPages = new ArrayList<>();
		webPages.add(fancyWebPage);
		webPages.add(new IndexWebPage(info, javaDoc.clone(), fancyWebPage.getFile()));
		webPages.add(new RobotsTxt(info.getLocalGitHubWebRepository()));
		webPages.add(new SiteMap(info, fancyWebPage.getFile()));
		webPages.add(new PrintableWebPage(info, javaDoc.clone()));
		return webPages;
	}
}
