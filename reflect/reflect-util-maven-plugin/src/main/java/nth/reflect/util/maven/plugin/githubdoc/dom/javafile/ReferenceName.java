package nth.reflect.util.maven.plugin.githubdoc.dom.javafile;

import java.io.File;

/**
 * A {@link ReferenceName} is:
 * <ul>
 * <li>An internal reference name to a {@link JavaFile} or a resource file.</li>
 * <li>Is the full file path (relative to the given rootFolder), where slashes
 * and backward slashes are replaced with a {@link #SEPARATOR}</li>
 * <li>A {@link ReferenceName} is lower case only so that searching a
 * {@link ReferenceName} is case insensitive</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public class ReferenceName {

	private static final String SLASH = "/";
	private static final String BACK_SLASH = "\\";
	public static String SEPARATOR = "_";
	private final String referenceName;
	public static final String PREFIX = ReferenceName.class.getSimpleName() + SEPARATOR;

	public ReferenceName(File rootFolder, File file) {
		String filePath = file.getAbsolutePath();
		String relativePath = filePath.replace(rootFolder.getAbsolutePath(), "");
		String referenceName1 = replaceSlashesWithSeperators(relativePath);
		String referenceName2 = removeSeperatorsAtTheBegining(referenceName1);
		referenceName = PREFIX + referenceName2.toLowerCase();

	}

	private String removeSeperatorsAtTheBegining(String referenceName) {
		while (referenceName.startsWith(SEPARATOR)) {
			referenceName = referenceName.substring(1);
		}
		return referenceName;
	}

	private String replaceSlashesWithSeperators(String relativePath) {
		return relativePath.replace(BACK_SLASH, SEPARATOR).replace(SLASH, SEPARATOR);
	}

	public ReferenceName(String referenceName) {
		if (referenceName.startsWith(PREFIX)) {
			this.referenceName = referenceName;
		} else {
			this.referenceName = PREFIX + referenceName.toLowerCase();

		}

	}

	@Override
	public String toString() {
		return referenceName;
	}

	public ReferenceName addJavaExtensionIfMissing() {
		if (referenceName.toString().endsWith(JavaFile.FILE_EXTENSION)) {
			return this;
		} else {
			return new ReferenceName(referenceName + JavaFile.FILE_EXTENSION);
		}
	}

	public ReferenceName withFileName(String fileName) {
		if (fileName.equals("nth.reflect.fw.gui.component.mainwindow.MainWindow")) {
			System.out.println();
		}
		int posLastSeperator = referenceName.lastIndexOf(SEPARATOR);
		String preFix = referenceName.substring(0, posLastSeperator + SEPARATOR.length());
		return new ReferenceName(preFix + fileName.toLowerCase());
	}

	public String withoutPreFix() {
		if (referenceName.startsWith(PREFIX)) {
			return referenceName.substring(PREFIX.length());
		}
		return referenceName;
	}

	public String withoutPreFixAndJavaExtention() {
		String referenceNameWithoutPrefix = withoutPreFix();
		if (referenceNameWithoutPrefix.endsWith(JavaFile.FILE_EXTENSION)) {
			return referenceNameWithoutPrefix.substring(0,
					referenceNameWithoutPrefix.length() - JavaFile.FILE_EXTENSION.length());
		}
		return referenceNameWithoutPrefix;
	}

}
