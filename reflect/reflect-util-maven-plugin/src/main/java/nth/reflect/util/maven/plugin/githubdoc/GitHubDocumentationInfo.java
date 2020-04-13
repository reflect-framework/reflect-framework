package nth.reflect.util.maven.plugin.githubdoc;

import java.io.File;

import org.apache.maven.plugin.logging.Log;

public interface GitHubDocumentationInfo {

	File getProjectRootFolder();

	String getDocumentationRootClassName();

	String getGitHubUserName();

	String getGitHubPassword();

	String getGoalName();

	Log getLog();
}
