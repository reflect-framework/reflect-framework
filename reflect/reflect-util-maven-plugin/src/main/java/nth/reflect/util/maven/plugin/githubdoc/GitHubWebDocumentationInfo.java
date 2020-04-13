package nth.reflect.util.maven.plugin.githubdoc;

import java.io.File;

public interface GitHubWebDocumentationInfo extends GitHubDocumentationInfo {
	/**
	 * @return location of a local Git repository that contains the cloned GitHub
	 *         Web project
	 * 
	 */
	public File getLocalGitHubWebRepository();
}
