package nth.reflect.util.maven.plugin.githubdoc.dom.github;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import nth.reflect.util.maven.plugin.ReflectUtilMavenPlugin;
import nth.reflect.util.maven.plugin.githubdoc.GitHubDocumentationInfo;

public class GitRepository {

	public void commitAndPush(GitHubDocumentationInfo info, File locationLocalGitRepository) {

		try {

			StringBuilder path = new StringBuilder();
			path.append(locationLocalGitRepository.getAbsolutePath());
			path.append("/.git");
			Git git = new Git(new FileRepository(path.toString()));

			git.add().addFilepattern(".").call();

			String commitMessage = createCommitMessage(info);
			git.commit().setMessage(commitMessage).call();

			info.getLog().info("Git commit and push: " + path);

			UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider(info.getGitHubUserName(),
					info.getGitHubPassword());
			git.push().setPushAll().setRemote("origin").setCredentialsProvider(user).call();
			git.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private String createCommitMessage(GitHubDocumentationInfo info) {
		StringBuilder commitMessage = new StringBuilder();
		commitMessage.append("Updated pages with ");
		commitMessage.append(ReflectUtilMavenPlugin.MAVEN_ID);
		commitMessage.append(":");
		commitMessage.append(info.getGoalName());
		return commitMessage.toString();
	}

	public void deleteFolderContents(File folder) {
		File[] files = folder.listFiles();
		if (files != null) { // some JVMs return null for empty dirs
			for (File file : files) {
				if (file.isDirectory() && !file.getName().equals(".git")) {
					deleteFolderContents(file);
				}
				file.delete();
			}
		}
	}

}
