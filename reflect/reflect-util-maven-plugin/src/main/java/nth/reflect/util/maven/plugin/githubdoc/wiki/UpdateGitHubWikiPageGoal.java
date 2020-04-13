package nth.reflect.util.maven.plugin.githubdoc.wiki;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.jsoup.nodes.Document;

import nth.reflect.util.maven.plugin.githubdoc.GitHubDocumentationGoal;
import nth.reflect.util.maven.plugin.githubdoc.GitHubWikiDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.JavaDocFactory;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.DocumentationFiles;
import nth.reflect.util.maven.plugin.githubdoc.wiki.page.GitHubWikiPageService;

/**
 * <h3>Updating documentation on GitHub Wiki</h3>
 * <p>
 * You first you need to clone your Github Wiki repository (e.g.
 * https://github.com/{GIT_HUB_USER_NAME}/{REPOSITORY_NAME}.wiki.git) with your
 * <a href="https://nl.wikipedia.org/wiki/Integrated_development_environment"
 * >IDE</a> to your local hard disk. This you only need to do once.
 * </p>
 * <p>
 * You can than update the documentation to the GitHub Wiki by running the
 * {@link UpdateGitHubWikiPageGoal} with the following Maven command:
 * </p>
 * 
 * <pre>
 * mvn com.github.reflect-framework:reflect-util-maven-plugin:updateGitWikiPages -DdocumentationRootClassName={CLASS_NAME_OF_DOCUMENTATION_ROOT} -DlocalGitHubWikiRepository={FILE_LOCATION_LOCAL_GIT_HUB_WIKI_REPOSITORY} -DgitHubUserName={GIT_HUB_USER_NAME} -DgitHubPassword={GIT_HUB_PASSWORD}
 * </pre>
 * 
 * Where:
 * <ul>
 * <li>{CLASS_NAME_OF_DOCUMENTATION_ROOT}: The name of the class or interface
 * that is the root of your documentation that ties in all this other
 * documentation classes or interfaces with help of the in line insert tag.
 * E.G."ReflectDocumentation"</li>
 * <li>{FILE_LOCATION_LOCAL_GIT_HUB_WIKI_REPOSITORY}: Local file location of the
 * GitHub repository for the Wiki pages.
 * E.g.:"C:/Users/nilsth/.git/reflect-framework.wiki"</li>
 * <li>{GIT_HUB_USER_NAME}: UserTestObject name of GitHub account.
 * E.g.:"superman"</li>
 * <li>{GIT_HUB_PASSWORD}: Password of GitHub account. E.g.:"superSecret"</li>
 * </ul>
 * <p>
 * The {@link UpdateGitHubWikiPageGoal} will then parse the JavaDoc of the
 * {CLASS_NAME_OF_DOCUMENTATION_ROOT} and generate Wiki pages into the local
 * GitHub {FILE_LOCATION_LOCAL_GIT_HUB_WIKI_REPOSITORY}. This GitHub repository
 * will then be committed and pushed onto the GitHub server (effectively
 * publishing the Wiki documentation).
 * </p>
 * The updated GitHub Wiki documentation can then be found at
 * https://github.com/{GIT_HUB_USER_NAME}/{REPOSITORY_NAME}/wiki
 * 
 * @author Nils ten Hoeve
 *
 */

@Mojo(name = UpdateGitHubWikiPageGoal.GOAL, defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.COMPILE)
public class UpdateGitHubWikiPageGoal extends GitHubDocumentationGoal implements GitHubWikiDocumentationInfo {

	public static final String GOAL = "updateGitWikiPages";

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Started...");

		try {
			DocumentationFiles documentationFiles = new DocumentationFiles(this);
			Document javaDoc = JavaDocFactory.getAllJavaDoc(this, documentationFiles);

			GitHubWikiPageService gitHubWikiPageService = new GitHubWikiPageService();
			gitHubWikiPageService.createGitHubWikiPages(this, documentationFiles, javaDoc);

			getLog().info("Completed...");

		} catch (Exception e) {
			getLog().error(e);
			String message = "Failed...";
			throw new MojoExecutionException(message, e);
		}
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