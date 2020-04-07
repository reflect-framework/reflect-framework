package nth.reflect.maven.plugin.language.files.maven;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.project.MavenProject;

public class MavenProjectClassPathFactory {

	public static List<Path> createArchivePaths(MavenProject mavenProject) {
		try {
			List<String> classpaths = mavenProject.getCompileClasspathElements();
			List<Path> archivePaths = classpaths.stream().map(p -> Paths.get(p)).collect(Collectors.toList());
			return archivePaths;
		} catch (Exception e) {
			throw new RuntimeException("Error creating archive paths for a Maven project", e);
		}
	}

	public static URL[] createArchiveUrls(MavenProject project) {
		try {
			List<String> runtimeClasspathElements = project.getCompileClasspathElements();
			URL[] runtimeUrls = new URL[runtimeClasspathElements.size()];
			for (int i = 0; i < runtimeClasspathElements.size(); i++) {
				String element = runtimeClasspathElements.get(i);
				runtimeUrls[i] = new File(element).toURI().toURL();
			}
			return runtimeUrls;
		} catch (Exception e) {
			throw new RuntimeException("Error creating archive URL's for a Maven project", e);
		}
	}

}
