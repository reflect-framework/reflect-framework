package nth.reflect.util.maven.plugin.githubdoc.wikiandweb;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.jsoup.nodes.Document;

import nth.reflect.util.maven.plugin.githubdoc.GitHubDocumentationGoal;
import nth.reflect.util.maven.plugin.githubdoc.GitHubWebDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.GitHubWikiDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.JavaDocFactory;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.DocumentationFiles;
import nth.reflect.util.maven.plugin.githubdoc.web.UpdateGitHubWebPageGoal;
import nth.reflect.util.maven.plugin.githubdoc.web.page.GitHubWebPageService;
import nth.reflect.util.maven.plugin.githubdoc.wiki.UpdateGitHubWikiPageGoal;
import nth.reflect.util.maven.plugin.githubdoc.wiki.page.GitHubWikiPageService;

/**
 * <h3>Updating documentation on GitHub Wiki and GitHub Web</h3> The
 * {@link UpdateGitHubWikiAndWebPagesGoal} executes both
 * {@link UpdateGitHubWikiPageGoal} and {@link UpdateGitHubWebPageGoal}.
 * 
 * @author Nils ten Hoeve
 *
 */

@Mojo(name = UpdateGitHubWikiAndWebPagesGoal.GOAL, defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.COMPILE)
public class UpdateGitHubWikiAndWebPagesGoal extends GitHubDocumentationGoal
		implements GitHubWebDocumentationInfo, GitHubWikiDocumentationInfo {

	public static final String GOAL = "updateGitWikiAndWebPages";

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Started...");

		try {
			DocumentationFiles documentationFiles = new DocumentationFiles(this);
			Document javaDoc = JavaDocFactory.getAllJavaDoc(this, documentationFiles);

			GitHubWikiPageService gitHubWikiPageService = new GitHubWikiPageService();
			gitHubWikiPageService.createGitHubWikiPages(this, documentationFiles, javaDoc.clone());

			GitHubWebPageService gitHubWebPageService = new GitHubWebPageService();
			gitHubWebPageService.createGitHubWebPages(this, documentationFiles, javaDoc.clone());

			getLog().info("Completed...");

		} catch (Exception e) {
			getLog().error(e);
			String message = "Failed...";
			throw new MojoExecutionException(message, e);
		}
	}

	@Parameter(property = "localGitHubWebRepository", required = true, readonly = true)
	private String localGitHubWebRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public File getLocalGitHubWebRepository() {
		return new File(localGitHubWebRepository);
	}

	@Parameter(property = "localGitHubWikiRepository", required = true, readonly = true)
	private String localGitHubWikiRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public File getLocalGitHubWikiRepository() {
		return new File(localGitHubWikiRepository);
	}

	@Override
	public String getGoalName() {
		return GOAL;
	}

}