package nth.reflect.util.maven.plugin.githubdoc;

import java.io.File;

import org.apache.maven.project.MavenProject;

public class ProjectRootDirectoryFinder {

	public static File findFor(MavenProject mavenProject) {
		MavenProject parent = mavenProject.getParent();
		if (parent != null) {
			return findFor(parent);
		} else {
			return mavenProject.getBasedir();
		}
	}

}
