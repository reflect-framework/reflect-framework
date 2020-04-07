package com.reflect.util.java.archive.file;

import java.nio.file.Path;
import java.util.jar.JarEntry;

public class FileInsideArchiveFactory {

	private static final String WINDOWS_SEPERATOR = "\\";
	private static final CharSequence SEPERATOR = "/";

	public static FileInsideArchive create(ClassLoader classLoader, Path archivePath, Path filePath) {
		String path = filePath.toString().replace(archivePath.toString(), "").replace(WINDOWS_SEPERATOR, SEPERATOR);
		path = path.substring(1);
		return create(classLoader, archivePath, path);
	}

	public static FileInsideArchive create(ClassLoader classLoader, Path archivePath, JarEntry entry) {
		String path = entry.getName();
		return create(classLoader, archivePath, path);
	}

	private static FileInsideArchive create(ClassLoader classLoader, Path archivePath, String path) {
		if (FileNameUtil.getExtension(path).toLowerCase().equals("class")) {
			return createClassFileInsideArchive(classLoader, archivePath, path);
		} else {
			return new FileInsideArchive(archivePath, path);
		}

	}

	private static FileInsideArchive createClassFileInsideArchive(ClassLoader classLoader, Path archivePath,
			String path) {
		String canonicalClassName = FileNameUtil.getWithoutExtention(path).replace("/", ".");
		try {
			Class<?> classInsideArchive = classLoader.loadClass(canonicalClassName);
			return new ClassFileInsideArchive(archivePath, path, classInsideArchive);
		} catch (Throwable e) {
			return new FileInsideArchive(archivePath, path);
		}
	}
}
