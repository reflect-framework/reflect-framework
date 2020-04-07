package com.reflect.util.java.archive.streamfactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.reflect.util.java.archive.file.FileInsideArchive;
import com.reflect.util.java.archive.file.FileInsideArchiveFactory;

public class DirectoryStreamFactory implements ArchiveFileStreamFactory {

	private final ClassLoader classLoader;

	public DirectoryStreamFactory(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public boolean canCreateFor(Path archivePath) {
		return Files.isDirectory(archivePath);
	}

	@Override
	public Stream<FileInsideArchive> createFor(Path archivePath) {
		try {
			Stream<FileInsideArchive> stream = Files.walk(archivePath).filter(p -> Files.isRegularFile(p))
					.map(filePath -> FileInsideArchiveFactory.create(classLoader, archivePath, filePath));
			return stream;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
