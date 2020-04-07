package com.reflect.util.java.archive.consumer;

import java.util.ArrayList;
import java.util.List;

import com.reflect.util.java.archive.file.FileInsideArchive;

public class FileInsideArchiveCollector implements Collector<FileInsideArchive> {

	private final List<FileInsideArchive> fileInsideArchives;

	public FileInsideArchiveCollector() {
		fileInsideArchives = new ArrayList();
	}

	@Override
	public void accept(FileInsideArchive fileInsideArchive) {
		fileInsideArchives.add(fileInsideArchive);
	}

	@Override
	public List<FileInsideArchive> getResults() {
		return fileInsideArchives;
	}
}
