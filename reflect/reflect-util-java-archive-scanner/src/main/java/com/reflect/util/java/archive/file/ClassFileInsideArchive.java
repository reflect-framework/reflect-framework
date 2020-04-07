package com.reflect.util.java.archive.file;

import java.nio.file.Path;

public class ClassFileInsideArchive extends FileInsideArchive {

	private Class<?> classInsideArchive;

	public ClassFileInsideArchive(Path archivePath, String path, Class<?> classInsideArchive) {
		super(archivePath, path);
		this.classInsideArchive = classInsideArchive;
	}

	public Class<?> getClassInsideArchive() {
		return classInsideArchive;
	}

}
