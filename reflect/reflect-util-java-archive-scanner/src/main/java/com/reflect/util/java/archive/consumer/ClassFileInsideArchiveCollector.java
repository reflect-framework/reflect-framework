package com.reflect.util.java.archive.consumer;

import java.util.ArrayList;
import java.util.List;

import com.reflect.util.java.archive.file.ClassFileInsideArchive;
import com.reflect.util.java.archive.file.FileInsideArchive;

public class ClassFileInsideArchiveCollector implements Collector<ClassFileInsideArchive> {

	private final List<ClassFileInsideArchive> classFilesInsideArchive;

	public ClassFileInsideArchiveCollector() {
		classFilesInsideArchive = new ArrayList();
	}

	@Override
	public void accept(FileInsideArchive fileInsideArchive) {
		if (fileInsideArchive instanceof ClassFileInsideArchive) {
			ClassFileInsideArchive classFileInsideArchive = (ClassFileInsideArchive) fileInsideArchive;
			classFilesInsideArchive.add(classFileInsideArchive);
		}
	}

	@Override
	public List<ClassFileInsideArchive> getResults() {
		return classFilesInsideArchive;
	}
}
