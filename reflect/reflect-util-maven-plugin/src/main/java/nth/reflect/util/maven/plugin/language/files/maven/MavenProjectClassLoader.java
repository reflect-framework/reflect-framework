package nth.reflect.util.maven.plugin.language.files.maven;

import java.net.URLClassLoader;

import org.apache.maven.project.MavenProject;

public class MavenProjectClassLoader extends URLClassLoader {

	// see https://stackoverflow.com/questions/871708/maven-plugin-cant-load-class
	public MavenProjectClassLoader(MavenProject project) {
		super(MavenProjectClassPathFactory.createArchiveUrls(project), Thread.currentThread().getContextClassLoader());
	}

}
