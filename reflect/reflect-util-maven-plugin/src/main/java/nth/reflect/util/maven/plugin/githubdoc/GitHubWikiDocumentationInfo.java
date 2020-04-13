package nth.reflect.util.maven.plugin.githubdoc;

import java.io.File;

public interface GitHubWikiDocumentationInfo extends GitHubDocumentationInfo {
	/**
	 * @return location of a local Git repository that contains the cloned GitHub
	 *         Wiki project
	 */
	public File getLocalGitHubWikiRepository();
}
