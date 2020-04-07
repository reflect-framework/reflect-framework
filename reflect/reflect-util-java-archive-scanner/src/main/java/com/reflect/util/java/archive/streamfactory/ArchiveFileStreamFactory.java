package com.reflect.util.java.archive.streamfactory;

import java.nio.file.Path;
import java.util.stream.Stream;

import com.reflect.util.java.archive.file.FileInsideArchive;

public interface ArchiveFileStreamFactory {

	public boolean canCreateFor(Path archivePath);

	public Stream<FileInsideArchive> createFor(Path archivePath);

}
