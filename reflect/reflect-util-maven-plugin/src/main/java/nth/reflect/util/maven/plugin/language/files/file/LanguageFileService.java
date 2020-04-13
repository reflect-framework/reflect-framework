package nth.reflect.util.maven.plugin.language.files.file;

import java.nio.file.Path;
import java.util.List;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.util.maven.plugin.language.files.framework.ReflectFrameworkFactory;
import nth.reflect.util.maven.plugin.language.files.texts.AllTexts;

public class LanguageFileService {

	public static void update(MavenProject mavenProject, Log log) {
		DependencyInjectionContainer container = ReflectFrameworkFactory.createContainer(mavenProject);

		AllTexts allTexts = new AllTexts(mavenProject, container);

		List<Path> languageFiles = LanguageFileFinder.find(mavenProject, container);

		for (Path languageFile : languageFiles) {
			update(languageFile, allTexts);
			log.info("Updated: " + languageFile.toString());
		}
	}

	private static void update(Path languageFilePath, AllTexts newTexts) {
		LanguageFile languageFile = new LanguageFile(languageFilePath);
		languageFile.update(newTexts);
	}

}
