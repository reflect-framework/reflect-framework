package com.reflect.util.java.archive.file;

import java.nio.file.Path;

public class FileInsideArchive {

	private final String simpleFileName;
	private final String packageName;
	private final Path archivePath;

	/**
	 * @param path, e.g. nth/reflect/ReflectFramework.class
	 */
	public FileInsideArchive(Path archivePath, String path) {
		this.archivePath = archivePath;
		this.simpleFileName = removePackagePath(path);
		this.packageName = removeLastSeparatorAndFileName(path).replace('/', '.');
	}

	private String removeLastSeparatorAndFileName(String name) {
		return name.replace("/" + simpleFileName, "");
	}

	private String removePackagePath(String name) {
		return name.replaceFirst(".*/", "");
	}

	public Path getArchivePath() {
		return archivePath;
	}

	public String getSimpleFileName() {
		return simpleFileName;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getSimpleFileNameWithoutExtension() {
		String simpleFileName = getSimpleFileName();
		String withoutExtention = FileNameUtil.getWithoutExtention(simpleFileName);
		return withoutExtention;
	}

	public String getExtension() {
		String simpleFileName = getSimpleFileName();
		String extention = FileNameUtil.getExtension(simpleFileName).toLowerCase();
		return extention;
	}

	@Override
	public String toString() {
		return getPackageName() + "." + getSimpleFileName();
	}

}
