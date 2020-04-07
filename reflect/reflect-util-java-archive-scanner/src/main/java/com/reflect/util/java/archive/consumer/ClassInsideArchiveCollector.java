package com.reflect.util.java.archive.consumer;

import java.util.ArrayList;
import java.util.List;

import com.reflect.util.java.archive.file.ClassFileInsideArchive;
import com.reflect.util.java.archive.file.FileInsideArchive;

public class ClassInsideArchiveCollector<T> implements Collector<Class<? extends T>> {

	private final List<Class<? extends T>> classesInsideArchive;

	public ClassInsideArchiveCollector() {
		classesInsideArchive = new ArrayList();
	}

	@Override
	public void accept(FileInsideArchive fileInsideArchive) {
		if (fileInsideArchive instanceof ClassFileInsideArchive) {
			ClassFileInsideArchive classFileInsideArchive = (ClassFileInsideArchive) fileInsideArchive;
			try {
				Class<? extends T> classFromArchive = (Class<? extends T>) classFileInsideArchive.getClassInsideArchive();
				classesInsideArchive.add(classFromArchive);
			} catch (Exception e) {
				// invalid type: no problem, we don't collect it
			}
		}
	}

	@Override
	public List<Class<? extends T>> getResults() {
		return classesInsideArchive;
	}
}
