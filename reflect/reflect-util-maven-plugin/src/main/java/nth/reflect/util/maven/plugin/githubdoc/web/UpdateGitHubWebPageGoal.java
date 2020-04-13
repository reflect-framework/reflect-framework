package nth.reflect.util.maven.plugin.githubdoc.web;

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
import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.JavaDocFactory;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.DocumentationFiles;
import nth.reflect.util.maven.plugin.githubdoc.web.page.GitHubWebPageService;

/**
 * *
 * <h3>Updating documentation on GitHub website</h3>
 * <p>
 * First you need to create a GitHub web site repository called
 * {GIT_HUB_USER_NAME}.github.io via https://github.com/new (see
 * https://pages.github.com/) and then clone the GitHub
 * {GIT_HUB_USER_NAME}.github.io repository (e.g.
 * https://github.com/ntenhoeve/ntenhoeve.github.io.git) in your <a href
 * ="https://nl.wikipedia.org/wiki/Integrated_development_environment">IDE</a>
 * to your local hard disk. This you only need to do once.
 * <p>
 * You can than update the documentation to the GitHub website by running the
 * {@link UpdateGitHubWebPageGoal} with the following Maven command:
 * </p>
 * 
 * <pre>
 * mvn com.github.reflect-framework:reflect-util-maven-plugin:updateGitWebPages -DdocumentationRootClassName={CLASS_NAME_OF_DOCUMENTATION_ROOT} -DlocalGitHubWebRepository={FILE_LOCATION_LOCAL_GIT_HUB_WEB_REPOSITORY} -DgitHubUserName={GIT_HUB_USER_NAME} -DgitHubPassword={GIT_HUB_PASSWORD}
 * </pre>
 * 
 * Where:
 * <ul>
 * <li>{CLASS_NAME_OF_DOCUMENTATION_ROOT}: The name of the class or interface
 * that is the root of your documentation that ties in all this other
 * documentation classes or interfaces with help of the in line insert tag.
 * E.G."ReflectDocumentation"</li>
 * <li>{FILE_LOCATION_LOCAL_GIT_HUB_WEB_REPOSITORY}: Local file location of the
 * GitHub repository for the website.
 * E.g.:"C:/Users/superman/.git/reflect-framework.github.io"</li>
 * <li>{GIT_HUB_USER_NAME}: UserTestObject name of GitHub account.
 * E.g.:"superman"</li>
 * <li>{GIT_HUB_PASSWORD}: Password of GitHub account. E.g.:"superSecret"</li>
 * </ul>
 * <p>
 * The {@link UpdateGitHubWebPageGoal} will then parse the JavaDoc of the
 * {CLASS_NAME_OF_DOCUMENTATION_ROOT} and generate Web pages into the local
 * GitHub {FILE_LOCATION_LOCAL_GIT_HUB_WEB_REPOSITORY}. This GitHub repository
 * will then be committed and pushed onto the GitHub server (effectively
 * publishing the Web documentation).
 * </p>
 * The updated GitHub Web documentation can then be found at
 * http://{GIT_HUB_USER_NAME}.github.io
 * 
 * 
 * @author Nils ten Hoeve
 *
 */

@Mojo(name = UpdateGitHubWebPageGoal.GOAL, defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.COMPILE)
public class UpdateGitHubWebPageGoal extends GitHubDocumentationGoal implements GitHubWebDocumentationInfo {

	public static final String GOAL = "updateGitWebPages";

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Started...");

		try {
			DocumentationFiles documentationFiles = new DocumentationFiles(this);
			Document javaDoc = JavaDocFactory.getAllJavaDoc(this, documentationFiles);

			GitHubWebPageService gitHubWebPageService = new GitHubWebPageService();
			gitHubWebPageService.createGitHubWebPages(this, documentationFiles, javaDoc);

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

	@Override
	public String getGoalName() {
		return GOAL;
	}

}