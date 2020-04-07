package com.reflect.util.java.archive.filter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

import com.reflect.util.java.archive.file.FileInsideArchive;

public class AllFileFilter implements Predicate<FileInsideArchive> {

	@Override
	public boolean test(FileInsideArchive fileInsideArchive) {
		Path archivePath = fileInsideArchive.getArchivePath();
		return Files.isRegularFile(archivePath);
	}

}
