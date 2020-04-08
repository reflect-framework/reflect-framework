package nth.reflect.util.maven.plugin.language.files.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.project.MavenProject;

import com.reflect.util.java.archive.JavaArchiveScanner;
import com.reflect.util.java.archive.consumer.ClassFileInsideArchiveCollector;
import com.reflect.util.java.archive.file.ClassFileInsideArchive;
import com.reflect.util.java.archive.filter.SubClassFilter;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.util.maven.plugin.language.files.maven.MavenProjectClassPathFactory;

public class LanguageFileFinder {

	public static List<Path> find(MavenProject mavenProject, DependencyInjectionContainer container) {
		List<Path> languageFiles = findLanguageFilesFolderInParent(mavenProject, container);
		return languageFiles;
	}

	private static List<Path> findLanguageFilesFolderInParent(MavenProject mavenProject,
			DependencyInjectionContainer container) {
		Path pathToClasses = findPathToClasses(mavenProject, container);
		Path srcFolderPath = findSrcFolderInParent(pathToClasses, pathToClasses);
		List<Path> languageFiles = findLanguageFiles(srcFolderPath);
		return languageFiles;
	}

	private static List<Path> findLanguageFiles(Path srcFolderPath) {
		try {
			List<Path> languageFiles = Files.walk(srcFolderPath).filter(path -> isLanguageFile(path))
					.collect(Collectors.toList());
			if (languageFiles.size() > 0) {
				return languageFiles;
			} else {
				throw new RuntimeException("Could not find a language file in: " + srcFolderPath
						+ ". Create a new language (text) file, e.g. src/main/resources/language_de.properties Note that English language files are discouraged, because the English text is dirived from the program code and annotations.");
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not find access src folder: " + srcFolderPath, e);
		}
	}

	private static boolean isLanguageFile(Path path) {
		return Files.isRegularFile(path) && fileNameStartsWithLanguage(path)
				&& fileNameEndsWithPropertiesExtension(path);
	}

	private static boolean fileNameEndsWithPropertiesExtension(Path path) {
		return path.getFileName().toString().endsWith(".properties");
	}

	private static boolean fileNameStartsWithLanguage(Path path) {
		return path.getFileName().toString().startsWith(DefaultLanguageProvider.PREFIX_LANGUAGE_FILE);
	}

	private static Path findSrcFolderInParent(Path path, Path startPath) {
		Path parentPath = path.getParent();
		if (parentPath == null) {
			throw new RuntimeException("Could not find a src folder in the parent of: " + startPath);
		}
		try {
			Optional<Path> result = Files.walk(parentPath, 1).filter(p -> isSrcFolder(p)).findFirst();
			if (result.isPresent()) {
				return result.get();
			} else {
				return findSrcFolderInParent(parentPath, startPath);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not access folder: " + parentPath, e);
		}
	}

	private static boolean isSrcFolder(Path path) {
		if (path.getParent() == null) {
			// hit the root without finding the src folder
			return false;
		}
		if (!Files.isDirectory(path)) {
			return false;
		}
		String name = path.getFileName().toString();
		boolean isSrcName = name.equalsIgnoreCase("src");
		return isSrcName;
	}

	private static Path findPathToClasses(MavenProject mavenProject, DependencyInjectionContainer container) {
		List<ClassFileInsideArchive> classFilesInsideArchive = getReflectApplicationClassFileInArchives(mavenProject,
				container);
		if (classFilesInsideArchive.size() == 0) {
			throw new RuntimeException("Could not find a Reflect Application.");
		} else if (classFilesInsideArchive.size() > 1) {
			throw new RuntimeException("Found more then one Reflect Applications.");
		} else {
			return classFilesInsideArchive.get(0).getArchivePath();
		}
	}

	private static List<ClassFileInsideArchive> getReflectApplicationClassFileInArchives(MavenProject mavenProject,
			DependencyInjectionContainer container) {
		List<Path> archivePaths = MavenProjectClassPathFactory.createArchivePaths(mavenProject);
		JavaArchiveScanner scanner = new JavaArchiveScanner(archivePaths);

		Class<? extends ReflectApplication> applicationClass = container.get(ReflectApplication.class).getClass();
		SubClassFilter filter = new SubClassFilter(archivePaths, applicationClass);
		ClassFileInsideArchiveCollector collector = new ClassFileInsideArchiveCollector();
		List<ClassFileInsideArchive> classFilesInsideArchive = scanner.find(filter, collector);
		return classFilesInsideArchive;
	}

}
