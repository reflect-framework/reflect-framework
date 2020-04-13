package nth.reflect.util.maven.plugin.githubdoc.dom.javafile;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import nth.reflect.util.maven.plugin.githubdoc.GitHubDocumentationGoal;

public class DocumentationFiles {
	// private static final String ANY_FILE_EXTENSION = new
	// Regex().anyCharacter(Repetition.min(1)).literal(".")
	// .anyCharacter(Repetition.minMax(1, 10)).toString();
	private static final List<String> NONE_RESOURCE_EXTENSIONS = Arrays
			.asList(JavaFile.FILE_EXTENSION, ".jar", ".class", ".idx", ".pack");
	private final Map<ReferenceName, JavaFile> javaFiles;
	private final Map<ReferenceName, File> resourceFiles;
	private final File rootFolder;

	/**
	 * Creates a folder object that may contain {@link JavaFile}s,
	 * {@link ResourceFile}s
	 * 
	 * You can use this object to {@link #findJavaFile(String)}s or to
	 * {@link #findResourceFile(String)}s
	 * 
	 * @param updateGitHubWikiPageGoal the actual location of the source folder
	 */

	public DocumentationFiles(GitHubDocumentationGoal goal) {
		this.rootFolder = goal.getProjectRootFolder();
		javaFiles = new HashMap<>();
		resourceFiles = new HashMap<>();
		goal.getLog().info("Reading folder: " + rootFolder);

		FileFilter filter = createProjectFolderFilter();
		File[] projectFiles = rootFolder.listFiles(filter);
		for (File projectFolder : projectFiles) {
			populateFromFolder(projectFolder);
		}
	}

	private FileFilter createProjectFolderFilter() {
		return new FileFilter() {

			@Override
			public boolean accept(File file) {
				boolean isDirectory = file.isDirectory();
				boolean isValidProjectName = !file.getName().contains(".");
				return isDirectory && isValidProjectName;
			}
		};
	}

	public void populateFromFolder(File folder) {
		File[] children = folder.listFiles();
		if (children != null) {
			for (File child : children) {
				if (child.isDirectory()) {
					// recursive call
					populateFromFolder(child);
				} else if (isJavaFile(child)) {
					ReferenceName referenceName = new ReferenceName(rootFolder, child).addJavaExtensionIfMissing();
					JavaFile javaFile = new JavaFile(this, child, referenceName);

					javaFiles.put(referenceName, javaFile);
				} else if (isResourceFile(child)) {
					ReferenceName referenceName = new ReferenceName(rootFolder, child);
					resourceFiles.put(referenceName, child);
				}
			}
		}
	}

	private boolean isResourceFile(File path) {
		int lastDotPos = path.getName().lastIndexOf(".");
		if (lastDotPos > 0) {
			String extension = path.getName().substring(lastDotPos).toLowerCase();
			boolean isResourceFile = !NONE_RESOURCE_EXTENSIONS.contains(extension);
			return isResourceFile;
		}
		return false;
	}

	private boolean isJavaFile(File path) {
		return path.getName().toLowerCase().endsWith(JavaFile.FILE_EXTENSION);
	}

	/**
	 * Tries to find a {@link JavaFile}
	 * 
	 * @param referenceName a (partial) {@link ReferenceName} to find
	 * @return search result
	 */
	public Optional<JavaFile> findJavaFile(ReferenceName referenceName) {
		Optional<ReferenceName> result = findJavaFileResourceName(referenceName);
		if (result.isPresent()) {
			JavaFile javaFile = javaFiles.get(result.get());
			return Optional.of(javaFile);
		}
		return Optional.empty();
	}

	public Optional<ReferenceName> findJavaFileResourceName(ReferenceName referenceName) {
		referenceName = referenceName.addJavaExtensionIfMissing();
		ReferenceNameFindIterator iterator = new ReferenceNameFindIterator(referenceName);
		Set<ReferenceName> referenceNames = javaFiles.keySet();
		while (iterator.hasNext()) {
			String referenceNameToFind = iterator.next();
			Optional<ReferenceName> result = referenceNames
					.stream()
					.filter(r -> r.toString().endsWith(referenceNameToFind))
					.findFirst();
			if (result.isPresent()) {
				return result;
			}
		}
		return Optional.empty();
	}

	/**
	 * Tries to find a resource file:
	 * <ul>
	 * <li>First by looking in same folder</li>
	 * <li>Than than looking in the sub documentationFiles</li>
	 * <li>And if it can't be found it will look ask the parent
	 * documentationFiles</li>
	 * </ul>
	 * 
	 * @param nameToFind (case insensitive), e.g. Image.png or image.png
	 * @return search result
	 */

	public Optional<File> findResourceFile(ReferenceName referenceName) {
		Optional<ReferenceName> result = findResourceFileResourceName(referenceName);
		if (result.isPresent()) {
			File resourceFile = resourceFiles.get(result.get());
			return Optional.of(resourceFile);
		}
		return Optional.empty();
	}

	public Optional<ReferenceName> findResourceFileResourceName(ReferenceName referenceName) {
		ReferenceNameFindIterator iterator = new ReferenceNameFindIterator(referenceName);
		Set<ReferenceName> referenceNames = resourceFiles.keySet();
		while (iterator.hasNext()) {
			String referenceNameToFind = iterator.next();
			Optional<ReferenceName> result = referenceNames
					.stream()
					.filter(r -> r.toString().endsWith(referenceNameToFind))
					.findFirst();
			if (result.isPresent()) {
				return result;
			}
		}
		return Optional.empty();
	}

}
