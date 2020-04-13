package nth.reflect.util.maven.plugin.githubdoc;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import nth.reflect.util.maven.plugin.ReflectUtilMavenPlugin;

/**
 * <h3>Git Hub Documentation goals</h3> {@link GitHubDocumentationGoal}s read
 * <a href="http://en.wikipedia.org/wiki/Javadoc">JavaDoc</a> of given class or
 * interface source files and convert these to:
 * <ul>
 * <li><a href="https://guides.github.com/features/wikis/">Git Hub Wiki
 * pages</a></li>
 * <li><a href="https://pages.github.com/">Git Hub Web pages</a></li>
 * </ul>
 * Only the class or interface description (the
 * <a href="http://en.wikipedia.org/wiki/Javadoc">JavaDoc</a> before the class
 * or interface keyword) is used.
 * <a href="http://en.wikipedia.org/wiki/Javadoc">JavaDoc</a> of fields and
 * methods or other members are not included.
 * 
 * <h3>The advantages</h3>
 * <ul>
 * <li>All your project documentation will be consistent (JavaDoc, manual, web
 * site and Wiki)</li>
 * <li>All your project documentation is directly available to the developer in
 * the form of JavaDoc</li>
 * <li>Basic formating can be used with HTML in JavaDoc</li>
 * <li>GitHub Html page and or Wiki pages can automatically be committed
 * (uploaded)</li>
 * <li>All your project documentation is spelling checked and grammar checked
 * when during editing (basic functionality of most EDI's)</li>
 * </ul>
 * 
 * <h3>Creating GitHub Repositories</h3>
 * <p>
 * Getting started from scratch:
 * <ul>
 * <li>Create a GitHub account via https://github.com/join</li>
 * <li>Create a GitHub project via https://github.com/new</li>
 * <li>Clone the GitHub project repository (
 * https://github.com/{GIT_HUB_USER_NAME}/{REPOSITORY_NAME}.git) with your
 * <a href="https://nl.wikipedia.org/wiki/Integrated_development_environment"
 * >IDE</a> to your local hard disk</li>
 * </ul>
 * </p>
 * <h3>Making documentation</h3>
 * <ul>
 * <li>Add documentation in the JavaDoc of your classes.
 * <li>Create a class or interface that ties all the documentation together.
 * This Class or Interface file must contain JavaDoc with {@insert} tags to
 * refer to other Classes or Interfaces with JavaDoc that needs to be included
 * into the documentation. See the JavaDoc of the {@link ReflectUtilMavenPlugin}
 * class as an example</li>
 * <li>Updating the documentation to the GitHub server when the JavaDoc has been
 * updated (see next chapters).</li>
 * </ul>
 *
 * <h3>Conventions</h3>
 * <ul>
 * <li>You can use images with the HTM tag &lt;img href="pictureName.png"&gt;.
 * This image must be located in the same package.</li>
 * <li>You can include a main picture, which will be added at the beginning of
 * the documentation. This image must have the same name as the class name, have
 * the png extension and be located in the same package.</li>
 * <li>You can insert other class or interface files using the Javadoc in-line
 * tag @insert</li>
 * <li>You can include {@link Chapter}s by adding a MaterialAppBarTitle between
 * &lt;H1&gt; &lt;/H1&gt; tags</li>
 * <li>You can include {@link SubChapter}s by adding a MaterialAppBarTitle
 * between &lt;H2&gt; &lt;/H2&gt; tags</li>
 * <li>You can include {@link SubSubChapter}s by adding a MaterialAppBarTitle
 * between &lt;H3&gt; &lt;/H3&gt; tags. Sub {@link SubSubChapter}s are normally
 * not rendered in the table of contents</li>
 * <li>It is good practice to create a documentation class that ties all the
 * java files together in one document.</li>
 * </ul>
 * 
 * @author nilsth
 */
public abstract class GitHubDocumentationGoal extends AbstractMojo implements GitHubDocumentationInfo {

	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	private MavenProject mavenProject;
	private File projectRootFolder;

	@Override
	public File getProjectRootFolder() {
		if (projectRootFolder == null) {
			projectRootFolder = ProjectRootDirectoryFinder.findFor(mavenProject);
		}
		return projectRootFolder;
	}

	@Parameter(property = "documentationRootClassName", required = true, readonly = true)
	private String documentationRootClassName;

	@Override
	public String getDocumentationRootClassName() {
		return documentationRootClassName;
	};

	@Parameter(property = "gitHubUserName", required = true, readonly = true)
	private String gitHubUserName;

	@Override
	public String getGitHubUserName() {
		return gitHubUserName;
	}

	@Parameter(property = "gitHubPassword", required = true, readonly = true)
	private String gitHubPassword;

	@Override
	public String getGitHubPassword() {
		return gitHubPassword;
	}
}
